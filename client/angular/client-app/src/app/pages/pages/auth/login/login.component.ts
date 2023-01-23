import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../../../_services/auth.service';
import { TokenStorageService, } from '../../../../_services/token-storage.service';
import { fadeInUp400ms } from '../../../../../@vex/animations/fade-in-up.animation';
import { AlertService } from 'src/app/_services/alert.service';

@Component({
  selector: 'vex-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  animations: [
    fadeInUp400ms
  ]
})
export class LoginComponent implements OnInit {
  submitted = false;
  form: UntypedFormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  inputType = 'password';
  visible = false;
  errorMessage = '';
  roles: string[] = [];
  loading = false;
  returnUrl: string;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private fb: UntypedFormBuilder,
    private cd: ChangeDetectorRef,
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private alertService: AlertService
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }

    // reset login status
    // this.authService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  toggleVisibility() {
    if (this.visible) {
      this.inputType = 'password';
      this.visible = false;
      this.cd.markForCheck();
    } else {
      this.inputType = 'text';
      this.visible = true;
      this.cd.markForCheck();
    }
  }

  get f() { return this.form.controls; }

  login(): void {
    this.submitted = true;
    if (this.form.invalid)
      return;

    this.authService.login(this.form.value)
      .subscribe({
        next: (response) => {
          this.tokenStorage.saveToken(response.access_token);
          this.tokenStorage.saveUser(response);
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.router.navigate([this.returnUrl]);
        },
        error: (error) => {
          this.isLoginFailed = true;
          if (error.status === 401) {
            this.alertService.error("Username and Password not accepted!");
          } else {
            this.alertService.error(error.statusText);
          }
        }
      })
  }

}
