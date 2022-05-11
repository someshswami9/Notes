package com.innovatelabs.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note_Table")
class Note(@ColumnInfo(name = "text") val text: String) {
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "Id") var id: Int = 0

}