---
name: Lumina Maternal Health
colors:
  surface: '#f9f9ff'
  surface-dim: '#cfdaf2'
  surface-bright: '#f9f9ff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f0f3ff'
  surface-container: '#e7eeff'
  surface-container-high: '#dee8ff'
  surface-container-highest: '#d8e3fb'
  on-surface: '#111c2d'
  on-surface-variant: '#594043'
  inverse-surface: '#263143'
  inverse-on-surface: '#ecf1ff'
  outline: '#8d7072'
  outline-variant: '#e1bec1'
  surface-tint: '#b52046'
  primary: '#b52046'
  on-primary: '#ffffff'
  primary-container: '#ff5a79'
  on-primary-container: '#62001e'
  inverse-primary: '#ffb2ba'
  secondary: '#505f76'
  on-secondary: '#ffffff'
  secondary-container: '#d0e1fb'
  on-secondary-container: '#54647a'
  tertiary: '#00668a'
  on-tertiary: '#ffffff'
  tertiary-container: '#00a0d7'
  on-tertiary-container: '#003246'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#ffd9dc'
  primary-fixed-dim: '#ffb2ba'
  on-primary-fixed: '#400011'
  on-primary-fixed-variant: '#910031'
  secondary-fixed: '#d3e4fe'
  secondary-fixed-dim: '#b7c8e1'
  on-secondary-fixed: '#0b1c30'
  on-secondary-fixed-variant: '#38485d'
  tertiary-fixed: '#c4e7ff'
  tertiary-fixed-dim: '#7bd0ff'
  on-tertiary-fixed: '#001e2c'
  on-tertiary-fixed-variant: '#004c69'
  background: '#f9f9ff'
  on-background: '#111c2d'
  surface-variant: '#d8e3fb'
typography:
  display:
    fontFamily: Plus Jakarta Sans
    fontSize: 36px
    fontWeight: '700'
    lineHeight: 44px
    letterSpacing: -0.03em
  headline-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 28px
    fontWeight: '600'
    lineHeight: 36px
    letterSpacing: -0.02em
  headline-lg-mobile:
    fontFamily: Plus Jakarta Sans
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
    letterSpacing: -0.015em
  headline-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '600'
    lineHeight: 24px
    letterSpacing: -0.01em
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 26px
    letterSpacing: -0.01em
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 22px
    letterSpacing: -0.005em
  body-sm:
    fontFamily: Inter
    fontSize: 13px
    fontWeight: '400'
    lineHeight: 18px
    letterSpacing: 0em
  label-lg:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '500'
    lineHeight: 20px
    letterSpacing: 0em
  label-md:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '600'
    lineHeight: 16px
    letterSpacing: 0.02em
  label-caps:
    fontFamily: Inter
    fontSize: 11px
    fontWeight: '600'
    lineHeight: 14px
    letterSpacing: 0.06em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 1rem
  gutter-desktop: 1.5rem
  margin: 1.5rem
  margin-desktop: 3rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.25rem
  space-xl: 1.5rem
---

## Brand & Style

This design system establishes a clinical yet deeply empathetic digital environment tailored for maternal, prenatal, and postpartum healthcare. It balances medical-grade trustworthiness with intuitive warmth, taking cues from native health operating systems and contemporary cycle-tracking interfaces. 

The aesthetic is clean, restrained, and intentional:
- **Personality:** Clinically competent, gentle, reassuring, and dignified. It eschews infantile tropes, cartoonish 3D elements, and exaggerated candy gradients in favor of refined editorial clarity and calm assurance.
- **Design Movement:** Modern Clinical Minimalism. Structure is achieved through pristine white spaces, soft tonal containment, hair-thin division lines, and intentional micro-typography.
- **Emotional Intent:** Reduces cognitive burden, stress, and anxiety. Interfaces prioritize quiet utility, physiological rhythm tracking, and clear clinical communication.

## Colors

The palette relies on pure optical whites and quiet slate neutrals, punctuated decisively by an active Pink Coral for calls-to-action and cycle states.

### Palette Architecture
- **Primary Accent (`#FF5A79`):** Vibrant refined Pink Coral. Reserved strictly for primary interactive CTAs, active cycle phases, fertile window signifiers, and key physiological highlights.
- **Primary Text & Headings (`#1E293B`):** Deep Slate Dark. Delivers high-contrast legibility without the harshness of pure black.
- **Secondary & Meta Text (`#64748B`):** Slate Gray. Provides calibrated contrast for timestamps, units of measure, clinical guidance notes, and inactive icon states.
- **Tertiary Accent (`#38BDF8`):** Soft Sky Blue. Reserved for hydration tracking, clinical lab telemetry, fetal milestones, and non-cyclical vitals.
- **Canvas & Surfaces:**
  - Base Background: Pure White (`#FFFFFF`).
  - Secondary Canvas: Off-White Slate (`#F8FAFC`) and Subtle Warm Canvas (`#FAFAFA`) to delineate modules and background card groupings.
- **Hairlines & Dividers:** Subtle Neutral Border (`#F1F5F9`). Never exceed 1px stroke weight.

## Typography

The typographic hierarchy pairs the soft, human geometry of **Plus Jakarta Sans** for titles with the pragmatic, screen-optimized neutrality of **Inter** for clinical data, long-form insights, and operational labels.

- **Headlines (`Plus Jakarta Sans`):** Selected for warm, open terminals and reassuring curves. Headings set a friendly tone without sacrificing clinical authority. Tight tracking (`-0.02em` to `-0.03em`) maintains structural compactness.
- **Body & Data (`Inter`):** Selected for optical neutrality and numeric legibility. Ensure tabular figures (`tnum`) are enabled for cycle days, gestational weeks, weight, heart rates, and blood pressure telemetry.
- **Caps & Micro Labels:** Used sparingly for metric categorizations (e.g., `TRIMESTER 2`, `OVULATION WINDOW`). Always styled with increased letter-spacing (`0.06em`) to ensure legibility on retina mobile screens.

## Layout & Spacing

The layout philosophy is disciplined and airy, emphasizing calm focus over visual density. 

### Grid & Canvas Structure
- **Mobile (Core Form Factor):** Single-column fluid stream bound by fixed `1.5rem` (`24px`) side margins. Gutters between multi-card metrics default to `1rem` (`16px`).
- **Tablet / Desktop:** 12-column layout centered at a maximum constraint of `1120px` to retain an intimate, dashboard-like feel. Margins scale to `3rem` (`48px`).
- **Internal Spacing Rhythms:** Cards utilize a default internal padding of `1.25rem` (`20px`) on mobile and scale to `1.5rem` (`24px`) on desktop. Metrics and data tiles use a compact `1rem` (`16px`) padding to balance touch target size and glanceability.
- **Section Breaks:** Keep a vertical gap of `2rem` (`32px`) between independent health domains (e.g., daily check-in vs. fetal size timeline).

## Elevation & Depth

Depth is treated with surgical delicacy. Stacked visual layers substitute heavy drops with light boundaries and optical separation.

- **Flat Surface Delineation:** Primary cards sit on `#FFFFFF` elevated off `#F8FAFC` backgrounds using a 1px boundary of `#F1F5F9`.
- **Low-Density Ambient Shadows:**
  - `shadow-xs`: `0px 1px 2px rgba(15, 23, 42, 0.04)` — Used for standard content tiles, list row items, and inactive chips.
  - `shadow-sm`: `0px 4px 12px rgba(15, 23, 42, 0.03), 0px 1px 3px rgba(15, 23, 42, 0.02)` — Used for floating summary cards, active tracking rings, and bottom sheets.
  - `shadow-coral`: `0px 6px 16px rgba(255, 90, 121, 0.22)` — Reserved exclusively for primary active buttons and floating tracker buttons.
- **Zero Heavy Borders:** Never use dark structural borders or inset bevels. Elevation should appear as ambient daylight cast over pure clinical stationery.

## Shapes

The design system adopts a reassuring, softly radiused physical form. Sharp corners are avoided to convey safety and approachability.

- **Primary Cards and Panels:** Use `rounded-lg` (`16px`) to `rounded-xl` (`20px`) for major informational containers and daily log modules.
- **Buttons and Chips:** Fully pill-shaped (`9999px`) for interactive touch anchors, quick-logging tags, and status badges.
- **Inputs & Text Fields:** Styled with `0.75rem` (`12px`) or `1rem` (`16px`) rounded corners to align with card nesting hierarchies.
- **Inner Nesting Rule:** When elements (such as secondary metrics or progress pills) sit nested inside an outer card (`20px`), their internal radius must scale proportionally down to `10px`–`12px` to prevent optical friction.

## Components

### Buttons
- **Primary CTA:** Background `#FF5A79`, text `#FFFFFF`, font `Inter` Medium (`15px`), height `52px`, pill radius (`9999px`). Elevation uses `shadow-coral`. No border.
- **Secondary Action:** Background `#FFFFFF`, text `#1E293B`, 1px solid border `#F1F5F9`, height `52px`, pill radius. In pressed state, surface shifts to `#F8FAFC`.
- **Ghost Action:** Background transparent, text `#64748B`, hover/focus text `#1E293B`.

### Cards & Modules
- Base structure: Background `#FFFFFF`, border `1px solid #F1F5F9`, radius `20px`, padding `20px`, elevation `shadow-xs`.
- Header area houses an icon in a soft pastel container (`36x36px`, 10px radius, `#FFF1F4` for coral variants), title in `Plus Jakarta Sans` (`16px`, semi-bold), and contextual trailing metadata in Slate Gray.

### Chips & Quick Symptom Selectors
- **Unselected:** Background `#F8FAFC`, border `1px solid transparent`, text `#64748B`, font `Inter` (`13px`, medium), radius `9999px`, padding `8px 16px`.
- **Selected:** Background `#FFF1F4`, border `1px solid #FF5A79`, text `#FF5A79`, font `Inter` (`13px`, semi-bold).

### Input Fields & Selectors
- Height `48px`, background `#FFFFFF`, border `1px solid #F1F5F9`, radius `12px`, padding `0 16px`, text `#1E293B`.
- **Focus State:** Border transitions to `#FF5A79` with a subtle focus ring: `0 0 0 3px rgba(255, 90, 121, 0.12)`.
- Helper labels sit above in `label-caps` (`Slate Gray`), strictly capitalized with `0.06em` tracking.

### Checkboxes & Radios
- Size `20x20px`. Unchecked has a clean `1.5px` border in `#CBD5E1` with `#FFFFFF` fill.
- Checked displays `#FF5A79` fill with a crisp white 1.5px checkmark/dot. Radius is `6px` for checkboxes and circular (`9999px`) for radios.

### Maternal Health Domain Components
- **Gestational / Cycle Dial:** A clean radial ring with a track in `#F1F5F9` and an active indicator in `#FF5A79` (stroke weight `8px`). Center metric displays the current week/day in `Plus Jakarta Sans` (`28px`, bold `#1E293B`) stacked above fruit/fetal development scale in Slate Gray (`13px`).
- **Telemetry Metric Tile:** Split two-up layout; each tile has a `#FAFAFA` surface, `16px` padding, `14px` border radius, micro-label (`11px` uppercase), and large numerical reading with tabular spacing.