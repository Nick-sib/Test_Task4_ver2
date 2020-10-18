package com.nickolay.testtask4ver2.ui.main


import com.nickolay.testtask4ver2.data.DataProviderImpl
import com.nickolay.testtask4ver2.data.entity.Employee
import com.nickolay.testtask4ver2.data.model.DataResult
import com.nickolay.testtask4ver2.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask4ver2.dbDataFormat
import com.nickolay.testtask4ver2.dbNameFormat
import com.nickolay.testtask4ver2.dbURLFormat
import com.nickolay.testtask4ver2.ui.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class MainViewModel : BaseViewModel<List<SpecialtyModel>>() {

    private var internetChanel = dataProvider.loadAllData()


    init {
        launch {
            internetChanel.consumeEach {
                @Suppress("UNCHECKED_CAST")
                when (it) {
                    is DataResult.Success<*> -> formatInternetDataToDb(it.data as List<Employee>)
                    is DataResult.Error -> loadRoomdata()//setError(it.error)
                }
            }
        }
    }


    private fun formatInternetDataToDb(data: List<Employee>) {
        //Можно показать сообщение что данные загружены и сохраняются на локальном устройстве
        dataProvider.clearAllData()
        data.forEach {
            val employeeId =
                dataProvider.addEmployee(
                    it.f_name.dbNameFormat(),
                    it.l_name.dbNameFormat(),
                    it.birthday.dbDataFormat(),
                    it.avatr_url.dbURLFormat()
                )
            it.specialty.forEach { specialty ->
                val specialtyID = dataProvider.addSpecialty(specialty.specialty_id, specialty.name)
                dataProvider.addCrossData(employeeId, specialtyID)
            }
        }
        loadRoomdata()
    }


    private fun loadRoomdata() = launch {
        val result = dataProvider.getAllSpecialties()
        if (result.isEmpty())
            setError(Throwable("EMPTY DATA"))
        else {
            setData(result)
        }
    }


    public override fun onCleared() {
        super.onCleared()
        internetChanel.cancel()
    }


}