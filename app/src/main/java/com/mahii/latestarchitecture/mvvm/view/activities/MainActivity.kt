package com.mahii.latestarchitecture.mvvm.view.activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.mahii.latestarchitecture.R
import com.mahii.latestarchitecture.dao.UserRoomDatabase
import com.mahii.latestarchitecture.databinding.ActivityMainBinding
import com.mahii.latestarchitecture.mvvm.model.User
import com.mahii.latestarchitecture.mvvm.view.adapters.UserAdapter
import com.mahii.latestarchitecture.mvvm.viewModel.UserViewModel
import com.mahii.latestarchitecture.utils.AppConstants.Companion.EXTRA_DESIGNATION
import com.mahii.latestarchitecture.utils.AppConstants.Companion.EXTRA_NAME
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity()/*, Observer*/ {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val User_REQUEST_CODE = 1

    private var mDB: UserRoomDatabase? = null

    private lateinit var users: ArrayList<User>
    private lateinit var userAdapter: UserAdapter

    //private lateinit var userViewModel: UserViewModelOld
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //userViewModel = UserViewModelOld(this)

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        //activityMainBinding.userViewModel = userViewModel
        //activityMainBinding.userViewModel = mUserViewModel

        users = ArrayList()
        userAdapter = UserAdapter(users)
        activityMainBinding.userList.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        //setUpObserver(userViewModel)

        mDB = UserRoomDatabase.getDatabase(this)

        mUserViewModel.getAllUsers().observe(this, object : android.arch.lifecycle.Observer<List<User>> {
            override fun onChanged(users: List<User>?) {
                Log.e("", ": ")
                if (users!!.isNotEmpty()) {
                    activityMainBinding.userList.visibility = View.VISIBLE
                    activityMainBinding.tvNoData.visibility = View.GONE
                } else {
                    activityMainBinding.userList.visibility = View.GONE
                    activityMainBinding.tvNoData.visibility = View.VISIBLE
                }
                userAdapter.setData(users as ArrayList<User>)
            }
        })

        activityMainBinding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddDataActivity::class.java)
            startActivityForResult(intent, User_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == User_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val name: String = data!!.getStringExtra(EXTRA_NAME)
            val designation: String = data.getStringExtra(EXTRA_DESIGNATION)
            val user = User(name, designation)
            mUserViewModel.insert(user)
        }
    }

    override fun onDestroy() {
        UserRoomDatabase.destroyInstance()
        super.onDestroy()
    }

    private fun setUpObserver(observable: Observable) {
        //observable.addObserver(this@MainActivity)
    }

    /*override fun update(o: Observable?, arg: Any?) {
        if (o is UserViewModelOld) {
            val userAdapter: UserAdapter = activityMainBinding.userList.adapter as UserAdapter
            userAdapter.setData(o.users)
        }
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_by_name -> {
                userAdapter.swap(usersSortedByName)
                true
            }
            R.id.sort_by_designation -> {
                userAdapter.swap(usersSortedByDesignation)
                true
            }
            R.id.sort_by_id -> {
                userAdapter.swap(usersSortedById)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val usersSortedById: List<User>
        get() = users.sortedByDescending { it.id }

    private val usersSortedByName: List<User>
        get() = users.sortedBy { it.name }

    private val usersSortedByDesignation: List<User>
        get() = users.sortedBy { it.designation }

}
