package com.example.week1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.domain.Task
import com.example.week1.viewmodel.TaskViewModel

@Composable
fun HomeScreen(taskViewModel: TaskViewModel = viewModel()) {
    var showDoneOnly by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp))
    {
        Spacer(modifier = Modifier.height(32.dp))

        Text("Omat tehtävät", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            Button(onClick = { showDoneOnly = !showDoneOnly })
            {
                Text(if (showDoneOnly) "Näytä kaikki" else "Näytä vain valmiit")
            }

            Button(onClick = { taskViewModel.sortByDueDate() })
            {
                Text("Lajittele")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(modifier = Modifier.fillMaxWidth())
        {
            OutlinedTextField(
                value = newTitle,
                onValueChange = { newTitle = it },
                label = { Text("Uusi tehtävä") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = newDescription,
                onValueChange = { newDescription = it },
                label = { Text("Kuvaus") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Button(
                onClick = {
                    if (newTitle.isNotBlank())
                    {
                        val task = Task(
                            id = (taskViewModel.tasks.maxOfOrNull { it.id } ?: 0) + 1,
                            title = newTitle,
                            description = newDescription,
                            priority = 2,
                            dueDate = "2026-01-14",
                            done = false
                        )
                        taskViewModel.addTask(task)
                        newTitle = ""
                        newDescription = ""
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Lisää")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter)
        {
            val displayedTasks = if (showDoneOnly) taskViewModel.filterByDone(true) else taskViewModel.tasks

            LazyColumn(modifier = Modifier.fillMaxWidth(0.95f))
            {
                item {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
                    {
                        Text("Tehtävä", modifier = Modifier.weight(2f))
                        Text("Kuvaus", modifier = Modifier.weight(3f))
                        Text("Prioriteetti", modifier = Modifier.weight(4f), textAlign = TextAlign.Left )
                        Text("Eräpäivä", modifier = Modifier.weight(3.5f), textAlign = TextAlign.Left)
                        Text("Tila", modifier = Modifier.weight(2f))
                    }
                    HorizontalDivider()
                }

                items(displayedTasks) { task ->
                    Row(
                        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp).padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(task.title, modifier = Modifier.weight(2f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(task.description, modifier = Modifier.weight(3f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(task.priority.toString(), modifier = Modifier.weight(1f))
                        Text(task.dueDate, modifier = Modifier.weight(1.5f))
                        Text(if (task.done) "Valmis" else "Kesken", modifier = Modifier.weight(1f))

                        Button(onClick = { taskViewModel.toggleDone(task.id) }, modifier = Modifier.padding(start = 8.dp))
                        {
                            Text("Vaihda")
                        }
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}