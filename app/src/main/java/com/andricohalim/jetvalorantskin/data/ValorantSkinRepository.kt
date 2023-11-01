package com.andricohalim.jetvalorantskin.data

import com.andricohalim.jetvalorantskin.model.FakeSkinDataSource
import com.andricohalim.jetvalorantskin.model.OrderSkin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ValorantSkinRepository {

    private val orderSkin = mutableListOf<OrderSkin>()

    init {
        if (orderSkin.isEmpty()) {
            FakeSkinDataSource.dummySkins.forEach{
                orderSkin.add(OrderSkin(it,0))
            }
        }
    }

    fun getAllSkin(): Flow<List<OrderSkin>> {
        return flowOf(orderSkin)
    }

    fun getOrderSkinById(rewardId: Long): OrderSkin {
        return orderSkin.first {
            it.skin.id == rewardId
        }
    }

    fun updateOrderSkin(skinId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderSkin.indexOfFirst { it.skin.id == skinId }
        val result = if (index >= 0) {
            val orderSkins = orderSkin[index]
            orderSkin[index] =
                orderSkins.copy(skin = orderSkins.skin, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderSkin(): Flow<List<OrderSkin>> {
        return getAllSkin()
            .map { orderRewards ->
                orderRewards.filter { orderReward ->
                    orderReward.count != 0
                }
            }
    }


    companion object {
        @Volatile
        private var instance: ValorantSkinRepository? = null

        fun getInstance(): ValorantSkinRepository =
            instance ?: synchronized(this) {
                ValorantSkinRepository().apply {
                    instance = this
                }
            }
    }
}
