package com.example.pagomovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "bank") var bank: String,
        @ColumnInfo(name = "dni") var dni: String,
        @ColumnInfo(name = "operator") var operator: String,
        @ColumnInfo(name = "phone") var phone: String
)
