package com.mahii.latestarchitecture

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.mahii.latestarchitecture.dao.UserRoomDatabase
import com.mahii.latestarchitecture.utils.AppUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDaoTest {

    private lateinit var userRoomDatabase: UserRoomDatabase

    @Before
    fun initDB() {
        userRoomDatabase =
                Room
                        .inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), UserRoomDatabase::class.java)
                        .build()
    }

    @After
    fun closeDB() {
        userRoomDatabase.close()
    }

    @Test
    fun insertData() {

        val cachedUser = AppUtils.cachedUser()
        userRoomDatabase.userDao().insert(cachedUser)

        val user = userRoomDatabase.userDao().getAllUsersOld()
        assert(user.isNotEmpty())
    }

    @Test
    fun retrieveData() {
        val users = AppUtils.cachedUsersList()
        users.forEach {
            userRoomDatabase.userDao().insert(it)
        }

        val retrievedUsers = userRoomDatabase.userDao().getAllUsersOld()
        assert(retrievedUsers == users.sortedWith(compareBy({ it.id }, { it.id })))
    }

    @Test
    fun clearDaoData() {
        val cachedUser = AppUtils.cachedUser()
        userRoomDatabase.userDao().insert(cachedUser)

        userRoomDatabase.userDao().clearTable()
        assert(userRoomDatabase.userDao().getAllUsersOld().isEmpty())
    }

}