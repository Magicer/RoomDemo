package xyz.magicer.roomdemo.data.relations.m2m

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subjects")
data class Subject constructor(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = 0
) {
    constructor() : this(0)

    //学科名称
    @ColumnInfo(name = "name")
    var name: String? = null
    //学科编码
    @ColumnInfo(name = "code")
    var code: String? = null

    override fun toString(): String {
        return "Subject(id=$id, name=$name, code=$code)"
    }


}