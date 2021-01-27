package com.kra.datamahasiswa.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentDb (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val nim: Long,
    val name: String
)