package com.andricohalim.jetvalorantskin.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andricohalim.jetvalorantskin.data.ValorantSkinRepository
import com.andricohalim.jetvalorantskin.model.OrderSkin
import com.andricohalim.jetvalorantskin.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ValorantSkinRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderSkin>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderSkin>>>
        get() = _uiState

    fun getAllSkin() {
        viewModelScope.launch {
            repository.getAllSkin()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderSkins ->
                    _uiState.value = UiState.Success(orderSkins)
                }
        }
    }
}