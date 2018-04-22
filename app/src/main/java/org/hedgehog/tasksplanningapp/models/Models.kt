package org.hedgehog.tasksplanningapp.models

import java.util.*

class Task(val id: Long,
           var name: String,
           var description: String,
           val created: Long,
           var deadline: Long,
           var subtasks: Array<Subtask>?,
           var status: String)

class Subtask(val id: Long,
              var name: String,
              var status: String,
              val owner: Long)

class Journal(val id: Long,
              val owner: String,
              var name: String,
              var description: String,
              val task: Long,
              val goal: Long,
              val date: Date)

class Goal(val id: Long,
            val owner: String,
            var name: String,
            var description: String,
            var tasks: Array<Task>,
            val created: Date,
            var deadline: Date)