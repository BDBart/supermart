import { Injectable } from '@angular/core';
import {Observable, of, Subject} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import { HttpClient } from "@angular/common/http";
import {User} from "../models/User";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = "http://localhost:9080/supermart_war/api/user";

  registeredUser = {} as User;
  loggedInUser = {} as User;
  loggedInUsername = new Subject<string>();

  createdMessage$ = new Subject<string>();
  emailMessage$ = new Subject<string>();
  loggedInMessage$ = new Subject<string>();
  loginAttemptMessage$ = new Subject<string>();

  constructor(private http: HttpClient) { }

  register(u: User): void {
    this.http.post<User>(`${this.url}/register`, u)
      .subscribe(
        data => {
          this.registeredUser = data;
          this.createdMessage$.next(`Account met email: ${data.email} is aangemaakt.`);
        },
        error => {
          console.log(error);
          this.createdMessage$.next(`Aanmaken account is mislukt. Reden: ${error.statusText}`);
        }
      )
  }

  login(u: User): void {
    this.http.post<User>(`${this.url}/login`, u)
      .subscribe(
        data => {
          this.loggedInUser = data;
          this.loggedInUsername.next(this.loggedInUser.email);
          this.loggedInMessage$.next('Ingelogd als ' + data.email);
          //even voor testen
          this.loginAttemptMessage$.next("Gebruiker is ingelogd");
          //hier router naar andere pagina
        }, error => {
          //hier als inlogpoging niet is geluk
          this.loginAttemptMessage$.next(error.statusText);
        }
      )
  }
}
