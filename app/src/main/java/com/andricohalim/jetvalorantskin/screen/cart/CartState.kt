package com.andricohalim.jetvalorantskin.screen.cart

import com.andricohalim.jetvalorantskin.model.OrderSkin

data class CartState(
    val orderSkin: List<OrderSkin>,
    val totalRequiredPoint: Int
)