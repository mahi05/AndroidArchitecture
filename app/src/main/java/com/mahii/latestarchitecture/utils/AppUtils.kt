package com.mahii.latestarchitecture.utils

import com.mahii.latestarchitecture.mvvm.model.User

class AppUtils {

    companion object {

        /* For Test */
        fun cachedUser() : User {
            return User("MaHii", "Android")
        }

        /* For Test */
        fun cachedUsersList() : ArrayList<User> {
            val list: ArrayList<User> = ArrayList()
            list.add(User("1", "1"))
            list.add(User("2", "2"))
            list.add(User("3", "3"))
            list.add(User("4", "4"))
            list.add(User("5", "5"))
            return list
        }
    }

}
