import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./Components/home/home.component";
import {ListprojetComponent} from "./Components/listprojet/listprojet.component";
import {ListTasksComponent} from "./Components/list-tasks/list-tasks.component";
import {HomeUserComponent} from "./Components/home-user/home-user.component";
import {ListProjetUserComponent} from "./Components/list-projet-user/list-projet-user.component";
import {ListTaskParProjetComponent} from "./Components/list-task-par-projet/list-task-par-projet.component";
import {ListAllTasksUserComponent} from "./Components/list-all-tasks-user/list-all-tasks-user.component";
import {DashboardUserComponent} from "./Components/dashboard-user/dashboard-user.component";
import {DashboardAdminComponent} from "./Components/dashboard-admin/dashboard-admin.component";
import {ProjectDetailsComponent} from "./Components/project-details/project-details.component";
import {LoginComponent} from "./Components/login/login.component";
import {HidenTasksAdminComponent} from "./Components/hiden-tasks-admin/hiden-tasks-admin.component";
import {EmployeesAdminComponent} from "./Components/employees-admin/employees-admin.component";

const routes: Routes = [
  { path: '', component:LoginComponent},
  { path: 'homeAdmin', component:HomeComponent,children:[
      {path:'DashbordAdmin',component:DashboardAdminComponent},
      {path:'listProjet',component:ListprojetComponent},
      {path:'detailsProject/:id',component:ProjectDetailsComponent},
      {path:'listTasks',component:ListTasksComponent},
      {path:'listTasksHidenForAdmin',component:HidenTasksAdminComponent},
      {path:'employees',component:EmployeesAdminComponent},

    ]},
  { path: 'homeUser', component:HomeUserComponent,children:[
      {path:'DashbordUser',component:DashboardUserComponent},
      {path:'listProjetUser',component:ListProjetUserComponent},
      {path:'listTasksParProject/:id',component:ListTaskParProjetComponent},
      {path:'listAllTasks',component:ListAllTasksUserComponent},


    ]},
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
