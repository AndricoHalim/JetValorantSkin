package com.andricohalim.jetvalorantskin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andricohalim.jetvalorantskin.data.ValorantSkinRepository
import com.andricohalim.jetvalorantskin.screen.cart.CartViewModel
import com.andricohalim.jetvalorantskin.screen.detail.DetailSkinViewModel
import com.andricohalim.jetvalorantskin.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ValorantSkinRepository) :
ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailSkinViewModel::class.java)) {
            return DetailSkinViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}