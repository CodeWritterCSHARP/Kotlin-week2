package com.example.week1.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.week1.domain.Task

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(listOf<Task>())
    private set
    init {
        tasks = listOf(
            Task(1, "Mennä kauppaan", "Maito, munat, kahvi", 2, "2026-01-15", false),
            Task(2, "Nukkumaan", "9 tuntia", 1, "2026-01-14", true),
            Task(3, "Katsoa TV:ta", "Uutiset", 3, "2026-01-20", false),
            Task(4, "Vaelluksen suunnittelu", "Valita missä metsässä", 1, "2026-01-13", false),
            Task(5, "Peutupa", "Pestä pyykkiä", 2, "2026-01-16", false)
        )
    }

    fun addTask(task: Task) {
        tasks = tasks + task
    }

    fun toggleDone(id: Int) {
        tasks = tasks.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(id: Int) {
        tasks = tasks.filterNot { it.id == id }
    }

    fun filterByDone(done: Boolean): List<Task> {
        return tasks.filter { it.done == done }
    }

    fun sortByDueDate() {
        tasks = tasks.sortedBy { it.dueDate }
    }
}