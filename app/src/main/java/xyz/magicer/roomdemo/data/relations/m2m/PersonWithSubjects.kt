package xyz.magicer.roomdemo.data.relations.m2m

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import xyz.magicer.roomdemo.data.relations.Person

data class PersonWithSubjects(

    @Embedded val person: Person,


    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PersonSubjectJoin::class,
            parentColumn = "p_id",
            entityColumn = "s_id"
        )
    )
    val subjects: List<Subject>
)
