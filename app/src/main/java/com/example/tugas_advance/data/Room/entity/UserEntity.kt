package com.example.tugas_advance.data.Room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Anggota")
data class UserEntity (
    @PrimaryKey val id : Int? = null,
    @ColumnInfo("nama") val nama : String,
    @ColumnInfo("role") val role : String
)