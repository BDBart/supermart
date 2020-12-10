import { Injectable } from '@angular/core';
import {Observable, of, Subject} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = "http://localhost:8060/resources/hello-world";

  constructor(private http: HttpClient) { }
}
