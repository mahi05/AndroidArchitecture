package com.mahii.latestarchitecture.mvvm.viewModel

import android.content.Context
import android.databinding.BaseObservable
import com.mahii.latestarchitecture.mvvm.model.User

class ItemViewModel constructor(context: Context, user: User) : BaseObservable() {

    fun setUser(user: User) {
        this.setUser(user)
        notifyChange()
    }

}
