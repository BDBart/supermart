import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {User} from "../../models/User";
import {ifTrue} from "codelyzer/util/function";

@Component({
  selector: 'app-registerpage',
  templateUrl: './registerpage.component.html',
  styleUrls: ['./registerpage.component.css']
})
export class RegisterpageComponent implements OnInit{

  addUserForm: FormGroup;
  user = {} as User;
  Cmessage$ = this.service.createdMessage$;
  _message$ = this.service.passwordMessage$;
  hideAdresInputs: boolean = false;

  // addUserForm = new FormGroup({
  //   email: new FormControl('', Validators.compose([Validators.required, Validators.email])),
  //   password: new FormControl('', Validators.required),
  //   akkoord: new FormControl(false, Validators.requiredTrue),
  //   adres: new FormControl('', Validators.required.),
  //   postcode: new FormControl(),
  //   woonplaats: new FormControl(),
  //   m_afhalen: new FormControl(false),
  //   t_afhalen: new FormControl(false),
  //   versturen: new FormControl(false),
  //   rembours: new FormControl(false)
  // });

  constructor(private service: UserService, private fb: FormBuilder) { }

  ngOnInit() {
    this.addUserForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['',[Validators.required]],
      akkoord: [false, [Validators.requiredTrue]],
      adres: [''],
      postcode: [''],
      woonplaats: [''],
      m_afhalen: [false],
      t_afhalen: [false],
      versturen: [false],
      rembours: [false],
    })
    this.addUserForm.get('t_afhalen').valueChanges
      .subscribe(value => {
        if (value){
          this.hideAdresInputs = true;
          this.addUserForm.get('adres').setValidators(Validators.required);
          this.addUserForm.get('postcode').setValidators(Validators.required);
          this.addUserForm.get('woonplaats').setValidators(Validators.required);
        } else {
          this.hideAdresInputs = false;
          this.addUserForm.get('adres').clearValidators();
          this.addUserForm.get('postcode').clearValidators();
          this.addUserForm.get('woonplaats').clearValidators();
        }
      })
  }

  addUser() : void {
    //aanroepen service om account aanmaken te POSTen
    this.service.register(this.user);
    //weer leegmaken van de user
    this.user = {} as User;
  }
}
