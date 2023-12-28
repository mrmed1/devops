import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Login} from "../../Models/login";

@Injectable({
  providedIn: 'root'
})
export class AuthServerService {

  constructor(private http: HttpClient) { }


  login(l:Login){
    return this.http.post('http://localhost:8080/api/auth/authenticate',l);
  }
}
