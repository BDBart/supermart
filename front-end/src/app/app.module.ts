import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { Route, RouterModule} from "@angular/router";
import { HomepageComponent } from './components/homepage/homepage.component';

const routes: Route[] = [
  { path: '', component: HomepageComponent },
  { path: 'home', component: HomepageComponent }
  ];

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
