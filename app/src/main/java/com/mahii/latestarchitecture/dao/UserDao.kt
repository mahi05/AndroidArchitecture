package com.mahii.latestarchitecture.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.mahii.latestarchitecture.mvvm.model.User

/*
* DAO :- DAO stands for DATA ACCESS OBJECT.
*        This is basically an interface, which contains the methods like getData() or storeData() etc.. which are used for accessing the database.
*        This interface will be implemented by the Room.
*
* . To interact or say access we need an interface annotated with DAO. This is how Room figures out.
*   Each DAO includes methods that offer abstract access to your app’s database.
*
* . By accessing a database using a DAO instead of a query builder or direct queries,
*   you can separate out different components of your database architecture.
*
* . DAO allows you to easily mock database access as your test app.
*
* . A DAO can be either an interface or an abstract class.
*   In case of an abstract class, it can optionally have an constructor that takes RoomDatabase as its only parameter.
*
* . Room creates each DAO implementation at compile time.
* */

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("SELECT * from user_data ORDER BY id ASC")
    fun getAllUsersOld(): List<User>

    @Query("SELECT * from user_data ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("DELETE FROM user_data")
    fun clearTable()

}
