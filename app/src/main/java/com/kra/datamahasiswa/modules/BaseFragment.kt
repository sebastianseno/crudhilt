package com.kra.datamahasiswa.modules

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    inline fun <T : Any, L : LiveData<T>> Fragment.observe(
        liveData: L,
        noinline body: (T?) -> Unit
    ) {
        liveData.observe(viewLifecycleOwner, Observer(body))
    }

    inline fun <T : Any, L : LiveData<T>> Fragment.observeNonNull(
        liveData: L,
        crossinline body: (T) -> Unit
    ) {
        liveData.observe(viewLifecycleOwner, Observer { it?.let(body) })
    }
}