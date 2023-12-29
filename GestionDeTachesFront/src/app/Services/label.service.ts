import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LabelService {
  private apiUrl='http://localhost:8085/api/';
  constructor(private http: HttpClient) { }
}
