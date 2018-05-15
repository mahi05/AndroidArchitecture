package com.mahii.latestarchitecture.mvvm.viewModel

import android.content.Context
import android.databinding.ObservableInt
import android.os.AsyncTask
import android.view.View
import com.mahii.latestarchitecture.dao.UserRoomDatabase
import com.mahii.latestarchitecture.mvvm.model.User
import java.util.*
import kotlin.collections.ArrayList

class UserViewModelOld constructor(context: Context) : Observable() {

    var userRecyclerView: ObservableInt
    var noDataMsg: ObservableInt
    var users: ArrayList<User> = ArrayList()
    var mDB: UserRoomDatabase? = null

    init {
        mDB = UserRoomDatabase.getDatabase(context)
        userRecyclerView = ObservableInt(View.GONE)
        noDataMsg = ObservableInt(View.GONE)
        fetchUsers()
    }

    private fun fetchUsers() {
        val task = DbAsyncTask(this@UserViewModelOld.mDB!!, object : DbAsyncTask.DbResponseListener {
            override fun onResponse(users: List<User>) {
                val usersList = (users as ArrayList<User>?)!!
                if (usersList.size > 0) {
                    userRecyclerView.set(View.VISIBLE)
                    noDataMsg.set(View.GONE)
                    changeUserDataSet(usersList)
                } else {
                    userRecyclerView.set(View.GONE)
                    noDataMsg.set(View.VISIBLE)
                }
            }
        })
        task.execute()
    }

    private fun changeUserDataSet(data: ArrayList<User>) {
        users.clear()
        users.addAll(data)
        setChanged()
        notifyObservers()
    }

    class DbAsyncTask constructor(
            private val mDB: UserRoomDatabase, private val listener: DbResponseListener) :
            AsyncTask<Void, Void, List<User>>() {

        override fun doInBackground(vararg voids: Void): List<User>? {
            return mDB.userDao().getAllUsersOld()
        }

        override fun onPostExecute(users: List<User>) {
            super.onPostExecute(users)
            listener.onResponse(users)
        }

        interface DbResponseListener {
            fun onResponse(users: List<User>)
        }
    }

}
