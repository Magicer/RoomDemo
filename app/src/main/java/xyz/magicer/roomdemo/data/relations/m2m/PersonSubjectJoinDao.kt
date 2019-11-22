package xyz.magicer.roomdemo.data.relations.m2m

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.magicer.roomdemo.data.relations.Person

@Dao
interface PersonSubjectJoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(psJoins: List<PersonSubjectJoin>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personSubjectJoin: PersonSubjectJoin)

    //获取选择了指定课程的所有人
    @Query(
        """
           SELECT * FROM persons
           INNER JOIN person_subject_join
           ON persons.id=person_subject_join.p_id
           WHERE person_subject_join.s_id=:subjectId
           """
    )
    suspend fun getPersonsForSubject(subjectId: Int): List<Person>

    //获取某人选的所有课程
    @Query(
        """
           SELECT * FROM subjects
           INNER JOIN person_subject_join
           ON subjects.id=person_subject_join.s_id
           WHERE person_subject_join.p_id=:personId
           """
    )
    suspend fun getSubjectsForPerson(personId: Int): List<Subject>

}