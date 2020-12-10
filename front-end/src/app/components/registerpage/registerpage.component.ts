import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-registerpage',
  templateUrl: './registerpage.component.html',
  styleUrls: ['./registerpage.component.css']
})
export class RegisterpageComponent {

  addUserForm = new FormGroup({
    email: new FormControl('', Validators.compose([Validators.required, Validators.email])),
    //add checkbox validation
    agree: new FormControl(false, Validators.requiredTrue)
  });

  addUser() : void {
    console.log("registratie");
  }
}
