package com.mahii.latestarchitecture.mvvm.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.mahii.latestarchitecture.dao.UserRepository
import com.mahii.latestarchitecture.mvvm.model.User

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: UserRepository = UserRepository(application)
    private var mAllUsers: LiveData<List<User>>

    init {
        mAllUsers = mRepository.users!!
    }

    fun getAllUsers(): LiveData<List<User>> {
        return mAllUsers
    }

    fun insert(user: User) {
        mRepository.insert(user)
    }

}
