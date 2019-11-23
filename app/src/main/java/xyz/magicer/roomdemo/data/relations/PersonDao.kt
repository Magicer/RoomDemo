package xyz.magicer.roomdemo.data.relations

import androidx.room.*

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(person: List<Person>)

    @Query("SELECT * FROM persons WHERE id = :id")
    suspend fun getPersonById(id: Int): Person

    @Transaction
    @Query(" SELECT * FROM persons")
    suspend fun getPersonPets(): List<PersonWithPets>

    @Transaction
    @Query(" SELECT * FROM persons WHERE id = :id ")
    suspend fun getPersonPetsById(id: Int): PersonWithPets

}