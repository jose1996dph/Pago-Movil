package com.example.pagomovil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Service(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "service") var service: String,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "alias") var alias: String
)