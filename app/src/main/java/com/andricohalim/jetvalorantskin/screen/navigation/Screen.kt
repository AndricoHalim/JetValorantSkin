package com.andricohalim.jetvalorantskin.screen.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailSkin : Screen("home/{skinId}") {
        fun createRoute(skinId: Long) = "home/$skinId"
    }
}