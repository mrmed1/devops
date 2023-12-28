import { Component, OnInit } from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from "@angular/cdk/drag-drop";

@Component({
  selector: 'app-list-task-par-projet',
  templateUrl: './list-task-par-projet.component.html',
  styleUrls: ['./list-task-par-projet.component.css']
})
export class ListTaskParProjetComponent implements OnInit {

  todo = ['Tâche 1', 'Tâche 2', 'Tâche 3'];
  inProgress = ['Tâche 4', 'Tâche 5'];
  done = ['Tâche 6', 'Tâche 7'];
  constructor() { }

  ngOnInit(): void {
  }
  drop(event: CdkDragDrop<string[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
    this.sortLists();

  }
  sortLists(): void {
    this.todo.sort();
    this.inProgress.sort();
    this.done.sort();
  }

}
