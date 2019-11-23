package xyz.magicer.roomdemo.data.relations.m2m

import xyz.magicer.roomdemo.data.AppDatabase

class PersonSubjectJoinRepository private constructor(private val psDao: PersonSubjectJoinDao) {

    suspend fun insertAll(vararg psjs: PersonSubjectJoin) = psDao.insertAll(psjs.toList())

    suspend fun getPersonsForSubject(subjectId: Int) = psDao.getPersonsForSubject(subjectId)

    suspend fun getSubjectsForPerson(personId: Int) = psDao.getSubjectsForPerson(personId)

    suspend fun getPersonWithSubjects() = psDao.getPersonWithSubjects()

    suspend fun getSubjectWithPersons() = psDao.getSubjectWithPersons()

    suspend fun getPersonWithSubjects(personId: Int) = psDao.getPersonWithSubjects(personId)


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