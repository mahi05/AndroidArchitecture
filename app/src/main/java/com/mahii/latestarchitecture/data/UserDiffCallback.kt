package com.mahii.latestarchitecture.data

import android.support.v7.util.DiffUtil
import com.mahii.latestarchitecture.mvvm.model.User

class UserDiffCallback(
        private val oldList: List<User>,
        private val newList: List<User>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

}