package xyz.magicer.roomdemo.data.relations.m2m

import xyz.magicer.roomdemo.data.AppDatabase

class PersonSubjectJoinRepository private constructor(private val psDao: PersonSubjectJoinDao) {

//    suspend fun insert(vararg psDao: PersonSubjectJoinDao) = psDao.insertAll(psDao.toList())

    //    suspend fun getPersonPets() = psDao.getPersonPets()
//    suspend fun getPersonPetsById(id: Int) = psDao.getPersonPetsById(id)
    suspend fun insertAll(vararg psjs: PersonSubjectJoin) = psDao.insertAll(psjs.toList())

    suspend fun getPersonsForSubject(subjectId: Int) = psDao.getPersonsForSubject(subjectId)

    suspend fun getSubjectsForPerson(personId: Int) = psDao.getSubjectsForPerson(personId)


    companion object {

        @Volatile
        private var instance: PersonSubjectJoinRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: PersonSubjectJoinRepository(AppDatabase.getInstance().personSubjectJoinDao()).also {
                        instance = it
                    }
            }
    }
}