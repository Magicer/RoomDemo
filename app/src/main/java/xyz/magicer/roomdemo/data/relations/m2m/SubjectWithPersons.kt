package xyz.magicer.roomdemo.data.relations.m2m

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import xyz.magicer.roomdemo.data.relations.Person

data class SubjectWithPersons(

    @Embedded val subject: Subject,


    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PersonSubjectJoin::class,
            parentColumn = "s_id",
            entityColumn = "p_id"
        )
    )
    val persons: List<Person>
)
