package com.path.stardelivery.ui.root

import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.path.stardelivery.R
import com.path.stardelivery.databinding.ActivityRootBinding
import com.path.stardelivery.ui.base.BaseActivity

class RootActivity : BaseActivity<RootViewModel, ActivityRootBinding>({ ActivityRootBinding.inflate(it) }) {

    override fun onBackPressed() {
        with(findNavController(R.id.nav_root_host_fragment)) {
            if (currentDestination != graph.first()) super.onBackPressed()
        }
    }
}