package xyz.magicer.roomdemo.data.relations

import androidx.room.*

@Entity(tableName = "persons")
data class Person constructor(
    @PrimaryKey @ColumnInfo(name = "id")
    var id: Int = 0
) {

    constructor():this(0)

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "address")
    var address: String? = null
    @ColumnInfo(name = "phone")
    var phone: String? = null

    @ColumnInfo(name = "sex")
    var sex:String?=null

    override fun toString(): String {
        return "Person(id=$id, name=$name, address=$address, phone=$phone, sex=$sex)"
    }

}