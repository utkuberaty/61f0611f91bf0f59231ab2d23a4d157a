package com.path.stardelivery.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.path.stardelivery.util.getViewModelViaKoin
import java.lang.reflect.ParameterizedType

open class BaseActivity<VM : ViewModel, VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    AppCompatActivity() {

    protected lateinit var viewBinding: VB

    val viewModel: VM by lazy { getViewModelViaKoin(viewModelClass) }

    private val viewModelClass: Class<VM> by lazy {
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = bindingFactory(layoutInflater)
        setContentView(viewBinding.root)
    }
}