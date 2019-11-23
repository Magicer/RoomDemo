package xyz.magicer.roomdemo.data.relations

import androidx.room.*
import xyz.magicer.roomdemo.data.converter.HouseConverter
import xyz.magicer.roomdemo.entity.House

@Entity(tableName = "persons")
@TypeConverters(HouseConverter::class)
data class Person constructor(
    @PrimaryKey @ColumnInfo(name = "id")
    var id: Int = 0
) {

    constructor() : this(0)

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "address")
    var address: String? = null
    @ColumnInfo(name = "phone")
    var phone: String? = null

    @ColumnInfo(name = "sex")
    var sex: String? = null

    @ColumnInfo(name = "migration_test")
    var migrationTest: String? = null

    @ColumnInfo(name = "migration_test_1")
    var migrationTest1: String? = null

    @ColumnInfo(name = "houses")
    var houses: List<House>? = null

    override fun toString(): String {
        return "Person(id=$id, name=$name, address=$address, phone=$phone, sex=$sex, migrationTest=$migrationTest, migrationTest1=$migrationTest1, houses=$houses)"
    }

//    override fun toString(): String {
//        return "Person(id=$id, name=$name, address=$address, phone=$phone, sex=$sex)"
//    }



}