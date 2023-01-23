import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConfigInitService } from '../init/config-init.service';
import { state } from '@angular/animations';
import { Router } from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private configInitService: ConfigInitService,
    private router: Router) { }

  private AUTH_API = this.configInitService.apiBaseUrl + '/oauth/token';

  login(credentials: any): Observable<any> {
    let requestBody = "username=" + credentials.username + "&password=" + credentials.password;
    return this.http.post(this.AUTH_API, requestBody, httpOptions);
  }

  logout() {
    window.sessionStorage.removeItem("auth-token");
    localStorage.removeItem("auth-token");
    this.router.navigate(['/login']);
  }
}
