import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { AuthGuard } from './_guards/index';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { VexModule } from '../@vex/vex.module';
import { HttpClientModule } from '@angular/common/http';
import { CustomLayoutModule } from './custom-layout/custom-layout.module';
import { ConfigInitService } from './init/config-init.service';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { AlertService } from './_services/alert.service';
import { MatSnackBarModule } from '@angular/material/snack-bar';
// import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,

    // Vex
    VexModule,
    CustomLayoutModule,
    MatSnackBarModule,
    //keycloak
    // KeycloakAngularModule
  ],
  providers: [
    AuthGuard,
    AlertService,
    authInterceptorProviders,
    // ConfigInitService,
    {
      provide: APP_INITIALIZER,
      multi: true,
      deps: [ConfigInitService],
      useFactory: (appConfigService: ConfigInitService) => () => appConfigService.loadAppConfig()

    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
