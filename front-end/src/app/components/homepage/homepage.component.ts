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
  loginFailed$ = this.service.loginAttemptMessage$;

  constructor(private service: UserService) { }

  loginAttemptForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  loginAttempt() : void{
    this.service.login(this.userLogin);
    this.userLogin = {} as User;
  }
}
