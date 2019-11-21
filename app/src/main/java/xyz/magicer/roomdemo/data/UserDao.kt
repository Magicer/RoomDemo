package xyz.magicer.roomdemo.data

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE id = :uid")
    suspend fun getUser(uid: Int): User

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)

    @Query("UPDATE users SET age = :age WHERE id = :id ")
    suspend fun updateAgeById(age: Int, id: Int)

}