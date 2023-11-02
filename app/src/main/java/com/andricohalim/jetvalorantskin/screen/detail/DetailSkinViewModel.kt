package com.andricohalim.jetvalorantskin.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andricohalim.jetvalorantskin.data.ValorantSkinRepository
import com.andricohalim.jetvalorantskin.model.OrderSkin
import com.andricohalim.jetvalorantskin.model.Skin
import com.andricohalim.jetvalorantskin.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailSkinViewModel(
    private val repository: ValorantSkinRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderSkin>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderSkin>>
        get() = _uiState

    fun getRewardById(skinId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderSkinById(skinId))
        }
    }

    fun addToCart(skin: Skin, count: Int) {
        viewModelScope.launch {
            repository.updateOrderSkin(skin.id, count)
        }
    }
}