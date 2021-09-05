package com.codinginflow.mvvmtodo.modal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class],version=1)
abstract class TaskDatabase  : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        private val applicationScope : CoroutineScope

        ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            //db operation
            val dao = database.get().taskDao()
            applicationScope.launch {
                dao.insert(Task("Create more tasks", important = true))
                dao.insert(Task("Study DBMS", important = true))
                dao.insert(Task("Revise OOP "))
                dao.insert(Task("Complete your project ", completed = true))
                dao.insert(Task("Pratice Leetcode"))
            }
        }
    }
}