package com.mahii.latestarchitecture.mvvm.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/*
* Entity :- A Java or a Kotlin class which represents a table within the database.
*
* Few points to be noted here
* . The class should be annotated with Entity, this is how Room identifies,
*   For each entity you create, a table is created with the associated Database.
*   By default, Room creates a column for each field, but you can avoid this for few fields by using Ignore annotation.
*
* . Each Entity, must define at least 1 primary key. You need to annotate the field with PrimaryKey annotation.
*
* . By default Room uses the class name as the table name. You can give custom name by using tableName property.
* */

@Entity(tableName = "user_data")
class User(
        @field:ColumnInfo(name = "name") var name: String,
        @field:ColumnInfo(name = "designation") var designation: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor() : this("", "")

}