import { Injectable } from '@angular/core';
import {Observable, of, Subject, throwError} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {User} from "../models/User";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = "http://localhost:9080/supermart_war/api/user";

  registeredUser = {} as User;
  loggedInUser = {} as User;
  loggedInUsername = new Subject<string>();

  createdFailedMessage$ = new Subject<string>();
  loginAttemptMessage$ = new Subject<string>();

  constructor(private http: HttpClient) { }

  register(u: User): void {
    this.http.post<User>(`${this.url}/register`, u)
      .subscribe(
        data => {
          this.registeredUser = data;
          alert(`Account met email: ${data.email} is aangemaakt`);
        },
        error => {
          console.log(error);
          this.createdFailedMessage$.next(`Er is al een account met dat email-adres...`);
        }
      )
  }

  login(u: User): void {
    this.http.post<User>(`${this.url}/login`, u)
      .subscribe(
        data => {
          this.loggedInUser = data;
          this.loggedInUsername.next(this.loggedInUser.email);
          localStorage.setItem('SessionUser', '1');
        }, error => {
          this.loginAttemptMessage$.next("Heb je wel de juiste gegevens ingevuld? Want uuhhm...: " + error.statusText);
        }
      )
  }
}
