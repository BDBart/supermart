import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/User";
import {UserService} from "../../services/user.service";
import {Subject} from "rxjs";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {

  userLogin = {} as User;
  loggedInUser$ = this.service.loggedInMessage$;
  loginFailed$ = this.service.loginAttemptMessage$;

  constructor(private service: UserService) {
  }

  loginAttemptForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  loginAttempt() : void{
    //service aanroepen om in te loggen
    this.service.login(this.userLogin);
    //user weer leegmaken
    this.userLogin = {} as User;
    //console.log("Er is iets gebeurd");
  }
}
