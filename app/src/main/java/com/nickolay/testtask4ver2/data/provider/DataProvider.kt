package com.nickolay.testtask4ver2.data.provider


import com.nickolay.testtask4ver2.data.model.DatasResult
import com.nickolay.testtask4ver2.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask4ver2.data.roomdb.specialty.SpecialtyModel
import kotlinx.coroutines.channels.ReceiveChannel

interface DataProvider {

    fun loadAllData(): ReceiveChannel<DatasResult>

    fun addEmployee(f_name: String, l_name: String, birthday: String, avatr_url: String): Long
    fun addSpecialty(specialtyId: Long, specialtyName: String): Long
    fun addCrossData(employeeId: Long, specialtyId: Long)
    fun clearAllDatas()

    suspend fun getAllSpecialties():List<SpecialtyModel>
    suspend fun getEmployeesById(id: Long): List<EmployeesModel>
    suspend fun getUserSpecialties(EmployeeId: Long): List<String>
}