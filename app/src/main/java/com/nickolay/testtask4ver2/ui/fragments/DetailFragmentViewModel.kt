package com.nickolay.testtask4ver2.ui.fragments

import com.nickolay.testtask4ver2.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DetailFragmentViewModel : BaseViewModel<List<String>>() {

    var id: Long? = 0
        set(value) {
            value?.also {
                requestSpecialties(it)
            } ?: run {
                setError(Throwable("EMPTY ID"))
            }
            field = value
        }


    private fun requestSpecialties(id: Long) = launch {
        dataProvider.getUserSpecialties(id).also {
            setData(it)
        }
    }

}