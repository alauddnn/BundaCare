package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ScreenRotation
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import kotlin.math.*

@Composable
fun InteractiveFetusVisualizer(
    modifier: Modifier = Modifier
) {
    // Rotation angles for 360-degree interactive dragging
    var rotationAngleY by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    // Heartbeat pulse animation
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.97f,
        targetValue = 1.03f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "heartbeat"
    )

    // Idle auto-rotation when not dragging
    LaunchedEffect(isDragging) {
        if (!isDragging) {
            while (true) {
                withFrameNanos {
                    rotationAngleY = (rotationAngleY + 0.35f) % 360f
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF2F5),
                        Color(0xFFFFFDFE),
                        BgGlobal
                    )
                )
            )
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { isDragging = true },
                    onDragEnd = { isDragging = false },
                    onDragCancel = { isDragging = false },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        rotationAngleY = (rotationAngleY + dragAmount.x * 0.7f) % 360f
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        // 360 badge top right
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .background(SurfaceWhite.copy(alpha = 0.92f), CircleShape)
                .padding(horizontal = 10.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ScreenRotation,
                    contentDescription = null,
                    tint = CoralPink,
                    modifier = Modifier.size(13.dp)
                )
                Text(
                    text = "360° Putar Interaktif",
                    fontSize = 11.sp,
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = CoralPink
                )
            }
        }

        // 3D Canvas Rendering
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 14.dp)
        ) {
            val centerX = size.width / 2f
            val centerY = size.height / 2f - 4f
            val baseRadius = min(size.width, size.height) * 0.38f

            // 1. Draw soft glowing amniotic fluid bubble
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFDDE5).copy(alpha = 0.45f),
                        Color(0xFFFFEEF2).copy(alpha = 0.25f),
                        Color.Transparent
                    ),
                    center = Offset(centerX, centerY),
                    radius = baseRadius * 1.35f
                ),
                radius = baseRadius * 1.35f,
                center = Offset(centerX, centerY)
            )

            // Amniotic bubble border ring
            drawCircle(
                color = CoralPinkLight,
                radius = baseRadius * 1.15f * pulseScale,
                center = Offset(centerX, centerY),
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f)
                )
            )

            // 2. 3D projection calculations for Fetus in embryonic curled position
            val radY = Math.toRadians(rotationAngleY.toDouble())
            val cosY = cos(radY).toFloat()
            val sinY = sin(radY).toFloat()

            fun project3D(x: Float, y: Float, z: Float): Offset {
                val rotX = x * cosY + z * sinY
                val scale = (1f + (rotX * 0.0015f)) * pulseScale
                return Offset(centerX + rotX * scale, centerY + y * scale)
            }

            // Umbilical cord spiral
            val cordPath = Path()
            var firstCord = true
            for (i in 0..16) {
                val t = i / 16f
                val cAngle = t * Math.PI.toFloat() * 3f
                val cRadius = 14f + t * 24f
                val cx = -18f + cos(cAngle) * cRadius
                val cy = 10f + t * 65f
                val cz = sin(cAngle) * cRadius
                val pt = project3D(cx, cy, cz)
                if (firstCord) {
                    cordPath.moveTo(pt.x, pt.y)
                    firstCord = false
                } else {
                    cordPath.lineTo(pt.x, pt.y)
                }
            }
            drawPath(
                path = cordPath,
                color = Color(0xFFFF9EB0).copy(alpha = 0.75f),
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = 4.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )

            // Fetus body parts (Ordered with depth sorting)
            // Head
            val headPt = project3D(0f, -48f, 0f)
            val headRadius = 38f * pulseScale
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFCCD5),
                        Color(0xFFFFA8BA),
                        Color(0xFFE2758E)
                    ),
                    center = headPt - Offset(headRadius * 0.25f, headRadius * 0.25f),
                    radius = headRadius * 1.1f
                ),
                radius = headRadius,
                center = headPt
            )

            // Curled Spine / Back
            val torsoPt = project3D(-18f * cosY, -4f, -18f * sinY)
            val torsoRadius = 32f * pulseScale
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFCCD5),
                        Color(0xFFFFA8BA),
                        Color(0xFFE2758E)
                    ),
                    center = torsoPt - Offset(10f, 10f),
                    radius = torsoRadius * 1.1f
                ),
                radius = torsoRadius,
                center = torsoPt
            )

            // Lower body / hips
            val hipPt = project3D(-24f * cosY, 36f, -24f * sinY)
            val hipRadius = 28f * pulseScale
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFCCD5),
                        Color(0xFFFFA8BA),
                        Color(0xFFE2758E)
                    ),
                    center = hipPt - Offset(8f, 8f),
                    radius = hipRadius * 1.1f
                ),
                radius = hipRadius,
                center = hipPt
            )

            // Tucked Arms near chin
            val armPt = project3D(18f * cosY, -14f, 18f * sinY)
            drawCircle(
                color = Color(0xFFFFBCC9),
                radius = 16f * pulseScale,
                center = armPt
            )

            // Tucked Legs / Little feet
            val legPt = project3D(14f * cosY, 44f, 14f * sinY)
            drawCircle(
                color = Color(0xFFFFBCC9),
                radius = 19f * pulseScale,
                center = legPt
            )

            // Heartbeat glow dot inside torso
            val heartPt = project3D(-5f * cosY, -10f, -5f * sinY)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        CoralPink,
                        CoralPink.copy(alpha = 0.5f),
                        Color.Transparent
                    ),
                    center = heartPt,
                    radius = 22f * pulseScale
                ),
                radius = 18f * pulseScale,
                center = heartPt
            )
        }

        // Swipe Hint bottom bar
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.TouchApp,
                contentDescription = null,
                tint = SlateGray,
                modifier = Modifier.size(13.dp)
            )
            Text(
                text = "Geser jari untuk melihat janin dari berbagai sudut",
                fontSize = 11.sp,
                fontFamily = InterFamily,
                color = SlateGray
            )
        }
    }
}
