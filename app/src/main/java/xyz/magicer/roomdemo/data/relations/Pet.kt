package xyz.magicer.roomdemo.data.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pets",
    foreignKeys = [ForeignKey(
        entity = Person::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("person_id"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]

)
data class Pet constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0
) {
    constructor() : this(0)

    @ColumnInfo(name = "name")
    var name: String? = null
    @ColumnInfo(name = "age")
    var age: Int? = null
    @ColumnInfo(name = "person_id")
    var personId: Int? = null

    override fun toString(): String {
        return "Pet(id=$id, name=$name, age=$age, personId=$personId)"
    }
}