package com.andricohalim.jetvalorantskin.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.andricohalim.jetvalorantskin.JetValorantSkinApp
import com.andricohalim.jetvalorantskin.R
import com.andricohalim.jetvalorantskin.assertCurrentRouteName
import com.andricohalim.jetvalorantskin.model.FakeSkinDataSource
import com.andricohalim.jetvalorantskin.onNodeWithStringId
import com.andricohalim.jetvalorantskin.screen.navigation.Screen
import com.andricohalim.jetvalorantskin.ui.theme.JetValorantSkinTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JetValorantSkinAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController
    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetValorantSkinTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetValorantSkinApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("SkinList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeSkinDataSource.dummySkins[10].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailSkin.route)
        composeTestRule.onNodeWithText(FakeSkinDataSource.dummySkins[10].name).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_cart).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("SkinList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeSkinDataSource.dummySkins[10].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailSkin.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithText(FakeSkinDataSource.dummySkins[4].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailSkin.route)
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}