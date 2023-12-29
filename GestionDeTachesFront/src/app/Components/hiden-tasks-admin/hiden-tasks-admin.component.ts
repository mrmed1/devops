import {AfterViewInit, Component, OnInit} from '@angular/core';
import {TaskService} from "../../Services/task.service";
import {Task} from "../../Models/task";

declare var $: any; // Import jQuery
@Component({
  selector: 'app-hiden-tasks-admin',
  templateUrl: './hiden-tasks-admin.component.html',
  styleUrls: ['./hiden-tasks-admin.component.css']
})
export class HidenTasksAdminComponent implements OnInit, AfterViewInit {
  tasks: Task[] = [];
  constructor(private taskService :TaskService,
              ) { }

  ngOnInit(): void {
    this.taskService.getAllTasks().subscribe(data => {
      console.log('data : ', data);
      this.tasks = (data as Task[]).filter(task => task.archived === true);

    },error => {
      console.log(error);
    }    )

  /*  this.tasks = [
      {
        "id": 1,
        "title": "Task 1",
        "description": "Description for Task 1",
        "startDate": "2023-12-20",
        "dueDate": "2023-12-27",
        "completed": false,
        "labels": [
          {
            "id": 1,
            "name": "Label 1"
          }
        ],
        "projectId": 1,
        "projectName": "Sample Project"
      },
      {
        "id": 2,
        "title": "Task 2",
        "description": "Description for Task 2",
        "startDate": "2023-12-20",
        "dueDate": "2023-12-27",
        "completed": false,
        "labels": [
          {
            "id": 2,
            "name": "Label 2"
          }
        ],
        "projectId": 1,
        "projectName": "Sample Project"
      }
    ]*/
  }

  ngAfterViewInit(): void {
    $.noConflict();
    $(document).ready(function() {
      $('#taskTable').DataTable();
    });
  }

  cancelHidden(id:number) {
    this.taskService.fromTrashToListTask(id).subscribe(data =>{
      console.log('data',data);
      console.log('succesfull cancel hidden !')
      setTimeout(()=>{
     window.location.reload();
      },1000)
    })

  }
}

