import { Component } from '@angular/core';
import {UserService} from "./services/user.service";
import {Router} from "@angular/router";
import {AuthGuardService} from "./services/auth-guard.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';

  constructor(private service: UserService, private router: Router, private authGuard: AuthGuardService) { }

  canDelete(): boolean {
    return this.authGuard.getToken();
  }

  logOff(): void {
    this.router.navigateByUrl('/home');
    this.service.loggedInUser = null;
    this.service.loggedInUsername.next('');
    localStorage.clear();
    alert('Je bent uitgelogd');
  }
}
