package xyz.magicer.roomdemo.data.relations.m2m

import xyz.magicer.roomdemo.data.AppDatabase

class SubjectRepository private constructor(private val subjectDao: SubjectDao) {

    suspend fun getSubjectById(id: Int) = subjectDao.getSubjectById(id)
    suspend fun insertAll(vararg subjects: Subject) = subjectDao.insertAll(subjects.toList())

    companion object {

        @Volatile
        private var instance: SubjectRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: SubjectRepository(AppDatabase.getInstance().subjectDao()).also {
                        instance = it
                    }
            }
    }
}