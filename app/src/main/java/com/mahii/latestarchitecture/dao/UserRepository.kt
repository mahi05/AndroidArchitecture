package com.mahii.latestarchitecture.dao

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.mahii.latestarchitecture.mvvm.model.User

class UserRepository(application: Application) {

    var userDao: UserDao
    var users: LiveData<List<User>>? = null

    init {
        val db = UserRoomDatabase.getDatabase(application)
        userDao = db!!.userDao()
        users = userDao.getAllUsers()
    }

    fun insert(user: User) {
        InsertAsyncTask(userDao).execute(user)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg params: User): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

}
