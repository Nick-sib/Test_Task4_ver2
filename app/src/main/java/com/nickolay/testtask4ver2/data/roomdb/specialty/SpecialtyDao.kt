package com.nickolay.testtask4ver2.data.roomdb.specialty

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SpecialtyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(specialtyModel: SpecialtyModel): Long

    @Query("SELECT *  FROM specialty_table ORDER BY specialtyName")
    fun getSpecialty(): List<SpecialtyModel>

    @Query("DELETE FROM specialty_table")
    fun deleteAll()
}