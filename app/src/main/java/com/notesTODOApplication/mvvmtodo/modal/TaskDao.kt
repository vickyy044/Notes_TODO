package com.notesTODOApplication.mvvmtodo.modal

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    fun getTasks(query: String, sortOrder: SortOrder, hideCompleted: Boolean)  : Flow<List<Task>> =
        when(sortOrder){
            SortOrder.BY_DATE -> getTasksSortedByDateCreated(query, hideCompleted)
            SortOrder.BY_NAME -> getTasksSortedByName(query, hideCompleted)
        }

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed=0) AND title Like '%' || :searchQuery || '%' ORDER BY important DESC, title")
    fun getTasksSortedByName(searchQuery : String, hideCompleted : Boolean):Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed=0) AND title Like '%' || :searchQuery || '%' ORDER BY important DESC, title")
    fun getTasksSortedByDateCreated(searchQuery : String, hideCompleted : Boolean):Flow<List<Task>>


    @Insert(onConflict = OnConflictStrategy.REPLACE )
     fun insert(task: Task)

    @Update
     fun update(task:Task)

    @Delete
     fun delete(task: Task)
}