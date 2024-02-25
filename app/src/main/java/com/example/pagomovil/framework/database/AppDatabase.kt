package com.example.pagomovil.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pagomovil.models.Contact

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase: RoomDatabase () {

    abstract fun contactDao(): ContactDao

    companion object {
        private const val DATABASE_NAME = "mobile_pay_db"

        fun getDatabase(context: Context): AppDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}