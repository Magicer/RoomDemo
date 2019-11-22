package xyz.magicer.roomdemo.data.relations

import xyz.magicer.roomdemo.data.AppDatabase

class PersonRepository private constructor(private val personDao: PersonDao) {

    suspend fun insert(vararg persons: Person) = personDao.insertAll(persons.toList())

    suspend fun getPersonPets() = personDao.getPersonPets()
    suspend fun getPersonPetsById(id: Int) = personDao.getPersonPetsById(id)

    companion object {

        @Volatile
        private var instance: PersonRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: PersonRepository(AppDatabase.getInstance().personDao()).also {
                        instance = it
                    }
            }
    }
}