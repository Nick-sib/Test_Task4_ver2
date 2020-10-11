package com.nickolay.testtask4ver2.data.roomdb.specialty

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "specialty_table")
class SpecialtyModel (
    @PrimaryKey
    val specialtyId: Long,
    val specialtyName: String
)