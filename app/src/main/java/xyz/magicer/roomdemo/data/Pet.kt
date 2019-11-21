package xyz.magicer.roomdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pets",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Person::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("person_id")
        )
    )

)
data class Pet constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int
) {
    constructor():this(0)

    @ColumnInfo(name = "name")
    var name: String? = null
    @ColumnInfo(name = "age")
    var age: Int? = null
    @ColumnInfo(name = "person_id")
    var personId: Int? = null
}