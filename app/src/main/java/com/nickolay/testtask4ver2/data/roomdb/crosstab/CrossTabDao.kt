package com.nickolay.testtask4ver2.data.roomdb.crosstab

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CrossTabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: CrossTabModel)

    @Query("DELETE FROM cross_table")
    fun deleteAll()

    @Query("SELECT employeesId FROM cross_table WHERE specialtyId = :idSpecialty")
    fun getEmployees(idSpecialty: Long): List<Long>

    @Query(
        "SELECT specialty_table.specialtyName " +
              "FROM  cross_table INNER JOIN specialty_table ON cross_table.specialtyId = specialty_table.specialtyId " +
              "WHERE cross_table.employeesId = :idEmployee"
    )
    fun getUserSpecialties(idEmployee: Long): List<String>

}