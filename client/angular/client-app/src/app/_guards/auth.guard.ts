import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { TokenStorageService } from '../_services/token-storage.service';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router, private tokenStorage: TokenStorageService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (this.tokenStorage.getToken()) {
            // console.log(this.tokenStorage.getUser())
            return true;
        }

        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
        return false;
    }
}