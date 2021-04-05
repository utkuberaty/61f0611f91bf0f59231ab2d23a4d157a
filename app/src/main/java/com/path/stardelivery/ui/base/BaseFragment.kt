package com.path.stardelivery.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.path.stardelivery.util.TAG
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
        Log.i(TAG,"onCreate")
        viewBinding = bindingFactory(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG,"onStart")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG,"onCreateView")
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG,"onViewCreated")
    }



    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume")
    }


}