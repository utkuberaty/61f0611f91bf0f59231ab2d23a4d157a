package com.path.stardelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.path.stardelivery.data.repository.StationRepository
import com.path.stardelivery.databinding.ActivityMainBinding
import com.path.stardelivery.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class RootActivity : BaseActivity<ActivityMainBinding>({ActivityMainBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}