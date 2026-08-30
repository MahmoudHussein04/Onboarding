**Features**
HorizontalPager: Displays 3 clean, well-structured introductory pages.

Interactive Dots Indicator: Tracks and highlights the current active page during swiping.

Dynamic Navigation Buttons:

A Skip button that triggers a confirmation pop-up (AlertDialog).

A Next button that automatically changes to Get Started on the final page.

Default Theme: Built using standard Material 3 system colors.

**Project Structure**
The code is modularized into separate files for better readability and maintainability:

OnboardingPageData.kt: Contains the data model class for individual page properties.

OnboardingPageContent.kt: Responsible for rendering the inner layout of each page (icon, title, description).

OnboardingScreen.kt: The main screen managing the Pager state, buttons, dots indicator, and dialog logic.

OnboardingActivity.kt: The entry point activity responsible for launching and hosting the onboarding flow.

