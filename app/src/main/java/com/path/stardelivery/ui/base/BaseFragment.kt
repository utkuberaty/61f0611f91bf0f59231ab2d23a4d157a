package com.path.stardelivery.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.path.stardelivery.util.getViewModelViaKoin
import java.lang.reflect.ParameterizedType

open class BaseFragment<VM : ViewModel, VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    Fragment() {

    protected lateinit var viewBinding: VB

    protected val viewModel: VM by lazy { getViewModelViaKoin(viewModelClass) }

    private val viewModelClass: Class<VM> by lazy {
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = bindingFactory(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }


}