import { Component } from '@angular/core';
import {UserService} from "./services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
  activeUser = false;

  constructor(private service: UserService, private router: Router) { }

  checkActiveUser(): void {
    if (this.service.loggedInUser == null){
      this.activeUser = false;
    } else {
      this.activeUser = true;
    }
  }

  logOff(): void {
    this.router.navigateByUrl('/home');
    this.service.loggedInUser = null;
    this.service.loggedInUsername.next('');
    alert('Je bent uitgelogd');
  }
}
