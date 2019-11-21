package xyz.magicer.roomdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User constructor(
    @PrimaryKey @ColumnInfo(name = "id") var uid: Int
) {
    constructor() : this(0)

    @ColumnInfo(name = "name")
    var name: String? = null

    //这里有默认值，升级数据库时需要注意
    @ColumnInfo(name = "age")
    var age: Int = 0

    @ColumnInfo(name = "address")
    var address: String? = null

    /**
     * 这里跟其他字段不一样哦。升级数据库的时候要注意
     */
    @ColumnInfo(name = "image")
    var image: String = ""

    @Ignore
    var password: String? = null

    //schema version 2
    @ColumnInfo(name = "phone")
    var phone: String? = null

//    //schema version 2
    //schema version 3时 从数据库中删除
//    @ColumnInfo(name = "email")
    @Ignore
    var email: String? = null


//    //schema version 2
//    override fun toString(): String {
//        return "User(uid=$uid, name=$name, age=$age, address=$address, image='$image', password=$password, phone=$phone, email=$email)"
//    }

//    schema version 1
    override fun toString(): String {
        return "User(uid=$uid, name=$name, age=$age, address=$address, image='$image', password=$password)"
    }

}
