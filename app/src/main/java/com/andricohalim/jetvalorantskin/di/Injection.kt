package com.andricohalim.jetvalorantskin.di

import com.andricohalim.jetvalorantskin.data.ValorantSkinRepository

object Injection {
    fun provideRepository(): ValorantSkinRepository {
        return ValorantSkinRepository.getInstance()
    }
}