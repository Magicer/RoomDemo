package xyz.magicer.roomdemo.data.relations

import xyz.magicer.roomdemo.data.AppDatabase

class PetRepository private constructor(private val petDao: PetDao) {

    suspend fun insert(pet: Pet) = petDao.insert(pet)
    suspend fun insertAll(vararg pets: Pet) = petDao.insertAll(pets.toList())

    companion object {

        @Volatile
        private var instance: PetRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: PetRepository(AppDatabase.getInstance().petDao()).also {
                        instance = it
                    }
            }
    }
}