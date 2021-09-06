package com.codinginflow.mvvmtodo.modal

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE title Like '%' || :searchQuery || '%' ORDER BY important DESC")
    fun getTasks(searchQuery : String):Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
     fun insert(task: Task)

    @Update
     fun update(task:Task)

    @Delete
     fun delete(task: Task)
}