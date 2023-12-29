import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {FormControlerService} from "../../Services/form-controles.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectService} from "../../Services/project.service";
import {Projet} from "../../Models/projet";
import {Task} from "../../Models/task";
import {TaskService} from "../../Services/task.service";
import {MemberService} from "../../Services/member.service";

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
  idMemberToAdd:number=0;


  constructor(private projectService: ProjectService,
              private taskService: TaskService,
              private memberService: MemberService,
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
        this.taskService.getTasksByProjectId(this.projetId).subscribe(tasks => {
          this.listTask=(tasks as Task[]).filter(task => task.archived === false);
          console.log('listTask  :', this.listTask);
        },error => {
          console.log(error);
        })
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

  assignLabelToTask(taskId: number,labelId: number,) {
    this.taskService.assiggnLabelToTask(taskId,labelId).subscribe(data => {
      console.log('succses assign label to task');
      console.log('data : ',data);
      window.location.reload();
    });

  }
markTaskAsDone(id:number){
    this.taskService.markAsDone(id).subscribe(()=>{
      console.log('task marked as done succesfully !');
      setTimeout(() => {
        window.location.reload();
      }, 1000);

    },error => {
      console.log(error);

    })


}
  addMember(idMember: number,idProject:number) {

    this.memberService.affecterMemberToProject(idMember,idProject).subscribe(data => {
      console.log('task deleted');
      setTimeout(() => {
        window.location.reload();
      },1000)
  },error => {
      console.log(error);
    })
  }

  getTaskById(id: any) {
    this.taskService.taskById(id).subscribe(data => {
        this.task = data as Task;
        console.log('task : ', this.task);
      this.formService.formGroupAddTask.patchValue({
        nameT: this.task.title,
        descriptionT: this.task.description,
        dateStartT:this.task.startDate,
      });
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
      console.log('task : ', this.editTask);
      this.taskService.updateTask(this.editTask).subscribe(data => {
        console.log('task updated');
        setTimeout(() => {
          //window.location.reload();
        }, 1000);
      });
    } else {
      console.log("Invalid form");
      this.validateAllFormFields(this.formService.formGroupAddTask);
    }
  }

  hideTask(id: any) {
    this.taskService.hideTask(id).subscribe(()=>{
      console.log('task hide succesfully !');
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    },error => {
      console.log(error);

    })

  }


}
