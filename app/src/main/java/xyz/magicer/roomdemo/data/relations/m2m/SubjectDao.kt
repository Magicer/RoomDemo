package xyz.magicer.roomdemo.data.relations.m2m

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(subjects: List<Subject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(s: Subject)

    @Query("SELECT * FROM subjects WHERE id = :id")
    suspend fun getSubjectById(id: Int): Subject
}