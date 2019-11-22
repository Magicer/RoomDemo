package xyz.magicer.roomdemo.data.relations

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pets: List<Pet>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pet: Pet)


}