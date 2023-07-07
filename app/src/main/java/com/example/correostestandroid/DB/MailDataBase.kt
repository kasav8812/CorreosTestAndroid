package com.example.correostestandroid.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.correostestandroid.Adapters.MailEntity

@Database(entities = arrayOf(MailEntity::class), version = 1, exportSchema = false)
abstract class MailDataBase : RoomDatabase() {

    abstract fun mailDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: MailDataBase? = null

        fun getDataseClient(context: Context) : MailDataBase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, MailDataBase::class.java, "MAIL_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}