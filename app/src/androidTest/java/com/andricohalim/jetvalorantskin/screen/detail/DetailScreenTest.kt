package com.andricohalim.jetvalorantskin.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.andricohalim.jetvalorantskin.model.OrderSkin
import com.andricohalim.jetvalorantskin.model.Skin
import com.andricohalim.jetvalorantskin.ui.theme.JetValorantSkinTheme
import com.andricohalim.jetvalorantskin.R
import com.andricohalim.jetvalorantskin.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val fakeOrderSkin = OrderSkin(
        skin = Skin(
            1,
            "https://static.wikia.nocookie.net/valorant/images/3/32/Bundle_Prime.png/revision/latest/scale-to-width-down/1000?cb=20200602093946",
            "Prime Bundle",
            7100,
            "The Prime Collection is a collection of cosmetics in VALORANT. Its contents were initially available to be obtained when the collection was first released as a bundle in the Store. After this, only its weapon skins can be obtained whenever they become available in a player's daily Featured Store Offers.",
            "Classic, Spectre, Guardian, Vandal, Knife"
        ),
        count = 0
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetValorantSkinTheme {
                DetailContent(
                    fakeOrderSkin.skin.photoUrl,
                    fakeOrderSkin.skin.name,
                    fakeOrderSkin.skin.requiredVP,
                    fakeOrderSkin.skin.description,
                    fakeOrderSkin.skin.weapon,
                    fakeOrderSkin.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeOrderSkin.skin.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.required_VP,
                fakeOrderSkin.skin.requiredVP
            )
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderSkin.skin.description).assertIsDisplayed()
    }

    @Test
    fun increaseProduct_buttonEnabled() {
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsNotEnabled()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsEnabled()
    }

    @Test
    fun increaseProduct_correctCounter() {
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    @Test
    fun decreaseProduct_stillZero() {
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))
    }
}