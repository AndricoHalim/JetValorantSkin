package com.andricohalim.jetvalorantskin.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andricohalim.jetvalorantskin.data.ValorantSkinRepository
import com.andricohalim.jetvalorantskin.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ValorantSkinRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getAddedOrderRewards() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderSkin()
                .collect { orderSkin ->
                    val totalRequiredPoint =
                        orderSkin.sumOf { it.skin.requiredVP * it.count }
                    _uiState.value = UiState.Success(CartState(orderSkin, totalRequiredPoint))
                }
        }
    }

    fun updateOrderSkin(skinId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderSkin(skinId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderRewards()
                    }
                }
        }
    }
}