/*
* Reference :- https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d
* */

package com.mahii.latestarchitecture.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.mahii.latestarchitecture.mvvm.model.User

/*
* Database :- This is an abstract class that extends RoomDatabase,
* this is where you define the entities (tables)and the version number of your database.
* It contains the database holder and serves as the main access point for the underlying connection.
*
* . You should follow the singleton design pattern when instantiating an AppDatabase object,
*   as each RoomDatabase instance is fairly expensive, and you rarely need access to multiple instances.
*
* . The class should be abstract and should extend RoomDatabase.
*
* */

@Database(entities = [(User::class)], version = 1)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context) : UserRoomDatabase? {
            if (INSTANCE == null) {

                synchronized(UserRoomDatabase::class.java) {

                    INSTANCE = Room.databaseBuilder<UserRoomDatabase>(context.applicationContext,
                            UserRoomDatabase::class.java, "word_database.db")
                            .fallbackToDestructiveMigration()
                            .build()

                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


}