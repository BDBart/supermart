import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthGuardService} from './services/auth-guard.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authGuard: AuthGuardService, private router: Router) { }

  canActivate(): boolean {
    if (!this.authGuard.getToken()) {
      this.router.navigateByUrl('/inloggen');
    }
    return this.authGuard.getToken();
  }
}
