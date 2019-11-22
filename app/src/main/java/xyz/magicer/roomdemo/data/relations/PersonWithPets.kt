package xyz.magicer.roomdemo.data.relations

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithPets(
    @Embedded val person: Person,
    @Relation(parentColumn = "id", entityColumn = "person_id")
    val pets: List<Pet>

) {
    override fun toString(): String {
        return "PersonWithPets(person=$person, pets=$pets)"
    }
}
