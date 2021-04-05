package com.path.stardelivery.ui.splash

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.path.stardelivery.databinding.FragmentSplashBinding
import com.path.stardelivery.ui.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>
    ({ FragmentSplashBinding.inflate(it) }) {

    private val navHostController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkSpaceship()
    }

    private fun checkSpaceship() {
        lifecycleScope.launch {
            delay(3000)
            viewModel.getStation().observe(this@SplashFragment){}
            val spaceShip = viewModel.getSpaceShip()?.spaceShip
            with(SplashFragmentDirections) {
                val destination = if (spaceShip != null)
                    actionSplashFragmentToHomeFragment()
                else actionSplashFragmentToCreateShipFragment()
                navHostController.navigate(destination)
            }
        }
    }
}