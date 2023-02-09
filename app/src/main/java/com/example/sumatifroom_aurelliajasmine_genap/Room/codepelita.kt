package com.example.sumatifroom_aurelliajasmine_genap.Room

import android.content.Context
import androidx.room.*

@Database (entities = [TbBarang::class], version = 1)
    abstract class codepelita : RoomDatabase() {
        abstract fun barangDAO() : BarangDAO

        companion object {
            @Volatile private var instance : codepelita? = null
            private val LOCK = Any()
            operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
                instance?: builDatabase(context).also {
                    instance = it
                }
            }
            private fun builDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                codepelita::class.java,
                "205387_db"
            ).build()
        }
    }