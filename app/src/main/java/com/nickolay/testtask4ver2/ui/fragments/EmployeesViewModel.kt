package com.nickolay.testtask4ver2.ui.fragments

import com.nickolay.testtask4ver2.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask4ver2.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class EmployeesViewModel : BaseViewModel<List<EmployeesModel>>() {

    var id: Long = 0
        set(value) {
            field = value
            requestUser()
        }


    private fun requestUser() = launch {
        dataProvider.getEmployeesById(id).also {
            setData(it)
        }
    }


}