package xyz.magicer.roomdemo.data.basic

import xyz.magicer.roomdemo.data.AppDatabase


class UserRepository private constructor(private val userDao: UserDao) {

    suspend fun getUsers() = userDao.getAllUsers()

    suspend fun insert(user: User) = userDao.insert(user)
    suspend fun delete(id: Int) = userDao.delete(User(id))
    suspend fun updateAgeById(age: Int, id: Int) = userDao.updateAgeById(age, id)


    companion object {
        val TAG = "UserRepository"

        @Volatile
        private var instance: UserRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: UserRepository(AppDatabase.getInstance().userDao()).also {
                    instance = it
                }
            }
    }
}