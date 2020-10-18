package com.nickolay.testtask4ver2.data.roomdb.crosstab

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cross_table")
data class CrossTabModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val employeesId: Long,
    val specialtyId: Long
)