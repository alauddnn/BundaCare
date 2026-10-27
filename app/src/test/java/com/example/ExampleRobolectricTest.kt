package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.NavTab
import com.example.ui.FiloViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Filo", appName)
  }

  @Test
  fun `test viewModel state updates`() {
    val viewModel = FiloViewModel()
    assertEquals(NavTab.BERANDA, viewModel.uiState.value.currentTab)

    viewModel.setTab(NavTab.JURNAL)
    assertEquals(NavTab.JURNAL, viewModel.uiState.value.currentTab)

    val initialKicks = viewModel.uiState.value.kickCount
    viewModel.recordKick()
    assertEquals(initialKicks + 1, viewModel.uiState.value.kickCount)

    viewModel.addWater()
    assertTrue(viewModel.uiState.value.waterLiters > 1.75f)
  }
}
