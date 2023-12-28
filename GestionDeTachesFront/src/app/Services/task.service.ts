import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl='http://localhost:8080/api/tasks';
  constructor(private http: HttpClient) { }
  getAllTasks(){
    return this.http.get(this.apiUrl);
  }
  updateTask(task:any){
    return this.http.put(this.apiUrl,task);
  }
  deleteTask(id:any){
    return this.http.delete(this.apiUrl+'/'+id);
  }
  addTask(task:any){
    return this.http.post(this.apiUrl,task);
  }
  markAsDone(taskId:any){
    return this.http.put(this.apiUrl+'/'+taskId+'markAsCompleted',null);
  }
  assiggnLabelToTask(taskId:any,labelId:any){
    return this.http.put(this.apiUrl+'/'+taskId+'/assignLabel/'+labelId,null);
  }
  hideTask(id:any){
    return this.http.put(this.apiUrl+'/'+id+'/to-trash',null);
  }
  fromTrashToListTask(id:any){
    return this.http.put(this.apiUrl+'/'+id+'/to-list-task',null);
  }
  getTasksByProjectId(projectId:any) {
    return this.http.get(this.apiUrl + '/' + projectId + '/tasks');
  }
  taskById(id:any){
    return this.http.get(this.apiUrl+'/'+id);
  }
  searchTask(keyword :string){
    return this.http.get(this.apiUrl+'/search?keyword='+keyword);
  }
  getLabels(){
    return this.http.get(this.apiUrl+'/labels');
  }
  getTaskByLabelId(labelId:any){
    return this.http.get(this.apiUrl+'/label/'+labelId+'/tasks');
  }
  getFilterTasks(labelIds:number,keyword:string,projectId:number,completed:boolean,
                 minStartDate: string,maxStartDate:string,
                 minDueDate:string,maxDueDate:string){

    return this.http.get(this.apiUrl+'/filter?labelIds='+labelIds+'&keyword='+keyword+
      '&projectId='+projectId+'&completed='+completed+
      '&minStartDate='+minStartDate+'&maxStartDate='+maxStartDate+
      '&minDueDate='+minDueDate+'&maxDueDate='+maxDueDate);
  }
  getFilterByStartDateAndProject(startDate :string,projectId:number){
    return this.http.get(this.apiUrl+'/filterByStartDateAndProject?startDate='+startDate+'&projectId='+projectId);
  }
  getFilterByLabel(labelId:number){
    return this.http.get(this.apiUrl+'/filterByLabel?labelId='+labelId);
  }

  getFilterByLabelAndProject(labelId:number,projectId:number){
    return this.http.get(this.apiUrl+'//filterByLabelAndProject?labelId='+labelId+'&projectId='+projectId);
  }
  getFilterByDueDate(dueDate:string){
    return this.http.get(this.apiUrl+'/filterByDueDate?dueDate='+dueDate);
  }
  getFilterByDueDateAndProject(dueDate:string,projectId:number){
    return this.http.get(this.apiUrl+'/filterByDueDateAndProject?dueDate='+dueDate+'&projectId='+projectId);
  }
  getFilterByCompletedAndProject(completed:boolean,projectId:number){
    return this.http.get(this.apiUrl+'/filterByCompletedAndProject?completed='+completed+'&projectId='+projectId);
  }

}
