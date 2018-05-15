package com.mahii.latestarchitecture.mvvm.view.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mahii.latestarchitecture.R
import com.mahii.latestarchitecture.databinding.ItemUserBinding
import com.mahii.latestarchitecture.mvvm.model.User

class UserAdapter constructor(private val usersList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemUserBinding: ItemUserBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_user, parent, false
        )
        return UserViewHolder(itemUserBinding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser(usersList[position])
    }

    class UserViewHolder constructor(private val itemUserBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemUserBinding.root) {
        fun bindUser(user: User) {
            if (itemUserBinding.user == null) {
                itemUserBinding.user = user
            } else {
                itemUserBinding.user
            }
        }
    }

    fun setData(data: ArrayList<User>) {
        usersList.clear()
        usersList.addAll(data)
        notifyDataSetChanged()
    }

}
