package xyz.magicer.roomdemo.data.relations.m2m

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import xyz.magicer.roomdemo.data.relations.Person

@Entity(
    tableName = "person_subject_join",
    primaryKeys = ["p_id", "s_id"],
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["p_id"]
        ),
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["s_id"]
        )]
)
data class PersonSubjectJoin constructor(
    @ColumnInfo(name = "p_id")
    val personId: Int,
    @ColumnInfo(name = "s_id")
    val subjectId: Int
) {
    override fun toString(): String {
        return "PersonSubjectJoin(personId=$personId, subjectId=$subjectId)"
    }
}