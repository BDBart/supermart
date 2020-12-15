import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Bezorgwijze, User} from "../../models/User";

@Component({
  selector: 'app-registerpage',
  templateUrl: './registerpage.component.html',
  styleUrls: ['./registerpage.component.css']
})
export class RegisterpageComponent {

  addUserForm = new FormGroup({
    email: new FormControl('', Validators.compose([Validators.required, Validators.email])),
    akkoord: new FormControl(false, Validators.requiredTrue),
    m_afhalen: new FormControl(false),
    t_afhalen: new FormControl(false),
    versturen: new FormControl(false),
    rembours: new FormControl(false)
  });

  user = {} as User;
  bezorgwijze = {} as Bezorgwijze;
  Cmessage$ = this.service.createdMessage$;
  _message$ = this.service.passwordMessage$;


  constructor(private service: UserService) { }

  addUser() : void {
    //aanroepen service om account aanmaken te POSTen
    this.service.register(this.user);
    //weer leegmaken van de user
    this.user = {} as User;
  }
}
