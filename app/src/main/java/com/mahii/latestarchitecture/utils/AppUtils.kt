package com.mahii.latestarchitecture.utils

import com.mahii.latestarchitecture.mvvm.model.User
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AppUtils {

    companion object {

        private const val COUNT = 100

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

        fun latestThings(interval: Long, timeUnit: TimeUnit): Flowable<List<User>> {
            return Flowable
                    .interval(0, interval, timeUnit, Schedulers.computation())
                    .map { cachedUsersList() }
        }

    }

}
