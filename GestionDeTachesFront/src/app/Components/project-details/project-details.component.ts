import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {FormControlerService} from "../../Services/form-controles.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../../Services/project.service";
import {Projet} from "../../Models/projet";
import {Task} from "../../Models/task";
import {TaskService} from "../../Services/task.service";

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {
  projetId: any;
  project: Projet = new Projet();
  listTask: Task[] = new Array<Task>();
  task: Task = new Task();
  editTask: Task = new Task();

  constructor(private projectService: ProjectService,
              private taskService: TaskService,
              private route: ActivatedRoute,
              public formService: FormControlerService,
  ) {
  }

  ngOnInit(): void {
    this.project = new Projet();

    this.route.params.subscribe(params => {
      const id = params['id'];
      this.projetId = id;

      this.projectService.getProjectById(this.projetId).subscribe(data => {
        this.project = data as Projet;
        console.log('project  :', this.project);
        this.listTask = this.project.tasks;
        console.log('listTask  :', this.listTask);

      });
    });

  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({onlySelf: true});
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }

  onSubmitTask() {
    if (this.formService.formGroupAddTask.valid) {
      console.log("Valid form");
      this.task.description = this.formService.formGroupAddTask.value.descriptionT;
      this.task.title = this.formService.formGroupAddTask.value.nameT;
      this.task.projectId = this.projetId;
      console.log('task : ', this.task);
      this.taskService.addTask(this.task).subscribe(data => {
        console.log('succses add task');
        console.log('data : ', data);
        this.listTask.push(data as Task);
        console.log('listTask : ', this.listTask);
        this.formService.formGroupAddTask.reset();
        window.location.reload();
      });

    } else {
      console.log("Invalid form");
      this.validateAllFormFields(this.formService.formGroupAddTask);

    }

  }

  onClear() {
    this.formService.formGroupAddTask.reset();
  }

  assignTask(projectId: number, taskId: number) {
    this.projectService.assignTask(projectId, taskId).subscribe(data => {
      console.log('succses assign task');
      window.location.reload();
    });

  }

  addMember(number: number) {

  }

  getTaskById(id: any) {
    this.taskService.taskById(id).subscribe(data => {
        this.task = data as Task;
        console.log('task : ', this.task);
      });

  }

  deleteTask(id: number) {
    this.taskService.deleteTask(id).subscribe(data => {
      console.log('task deleted');
      window.location.reload();
    });

  }

  updateTask() {
    if (this.formService.formGroupAddTask.valid) {
      this.editTask.description = this.formService.formGroupAddTask.value.descriptionT;
      this.editTask.title = this.formService.formGroupAddTask.value.nameT;
      this.editTask.projectId = this.projetId;
      this.editTask.completed=true;

      console.log('task : ', this.editTask);
      this.taskService.updateTask(this.editTask).subscribe(data => {
        console.log('task updated');
        window.location.reload();
      });
    } else {
      console.log("Invalid form");
      this.validateAllFormFields(this.formService.formGroupAddTask);
    }
  }

}
