import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { Route, RouterModule} from "@angular/router";
import { HomepageComponent } from './components/homepage/homepage.component';
import { RegisterpageComponent } from './components/registerpage/registerpage.component';
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import {AuthGuardService} from "./services/auth-guard.service";
import {AuthGuard} from "./AuthGuard";

const routes: Route[] = [
  { path: '', component: HomepageComponent },
  { path: 'home', component: HomepageComponent },
  { path: 'register', component: RegisterpageComponent }
  ];

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    RegisterpageComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [ AuthGuardService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
