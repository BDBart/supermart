import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {

  constructor() { }

  getToken(): any {
    return !!localStorage.getItem('SessionUser');
  }
}
