package com.path.stardelivery.ui.root

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.path.stardelivery.ui.home.favourites.FavouriteStationsAdapter

class RootViewModel: ViewModel() {

    val onTick = MutableLiveData<Long>()
    val onCountDownFinish = MutableLiveData<Boolean>()

    fun countDown(stabilityTime : Long) = object : CountDownTimer(stabilityTime, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.i("tic", "millisUntilFinished $millisUntilFinished")
            onCountDownFinish.value = false
            onTick.value = millisUntilFinished
        }

        override fun onFinish() {
            Log.i("onFinish", "finish")
            onCountDownFinish.value = true
        }
    }
}