package com.path.stardelivery.ui.home

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.path.stardelivery.R
import com.path.stardelivery.data.entities.SpaceShip
import com.path.stardelivery.databinding.FragmentHomeBinding
import com.path.stardelivery.ui.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>
    ({ FragmentHomeBinding.inflate(it) }) {

    private val navHostFragment by lazy {
        (childFragmentManager.findFragmentById(R.id.nav_home_host_fragment) as NavHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backPressDispatcher()
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        viewBinding.bottomNav.setupWithNavController(navHostFragment.navController)
    }


    private fun backPressDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        })
    }
}