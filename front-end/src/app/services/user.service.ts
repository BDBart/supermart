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

  createdMessage$ = new Subject<string>();
  passwordMessage$ = new Subject<string>();

  constructor(private http: HttpClient) { }

  register(u: User): void {
    this.http.post<User>(`${this.url}/register`, u)
      .subscribe(
        data => {
          this.registeredUser = data;
          this.createdMessage$.next(`Account met email: ${data.email} is aangemaakt.`)
          this.passwordMessage$.next(`Bekijk uw email ${data.email} voor uw wachtwoord.`);
        },
        error => {
          console.log(error);
          this.createdMessage$.next(`Aanmaken account is mislukt. Reden: ${error.statusText}`);
        }
      )
  }
}
