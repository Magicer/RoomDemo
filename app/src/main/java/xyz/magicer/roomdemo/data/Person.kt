package xyz.magicer.roomdemo.data

import androidx.room.*

@Entity(tableName = "persons")
data class Person constructor(
    @PrimaryKey @ColumnInfo(name = "id")
    var id: Int
) {

    constructor():this(0)

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "address")
    var address: String? = null
    @ColumnInfo(name = "phone")
    var phone: String? = null
}