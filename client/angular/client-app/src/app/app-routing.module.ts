import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CustomLayoutComponent } from './custom-layout/custom-layout.component';
import { VexRoutes } from '../@vex/interfaces/vex-route.interface';
import { QuicklinkModule, QuicklinkStrategy } from 'ngx-quicklink';
import { AuthGuard } from './_guards/index';

const routes: VexRoutes = [
  {
    path: 'login',
    loadChildren: () => import('./pages/pages/auth/login/login.module').then(m => m.LoginModule),
  },
  {
    path: 'register',
    loadChildren: () => import('./pages/pages/auth/register/register.module').then(m => m.RegisterModule),
  },
  {
    path: 'forgot-password',
    loadChildren: () => import('./pages/pages/auth/forgot-password/forgot-password.module').then(m => m.ForgotPasswordModule),
  },
  {
    path: 'coming-soon',
    loadChildren: () => import('./pages/pages/coming-soon/coming-soon.module').then(m => m.ComingSoonModule),
  },
  {
    path: '',
    canActivate: [AuthGuard],
    component: CustomLayoutComponent,
    children: [
      {
        path: 'dashboards/analytics',
        redirectTo: '/',
        pathMatch: 'full'
      },
      {
        path: '',
        loadChildren: () => import('./pages/dashboards/dashboard-analytics/dashboard-analytics.module').then(m => m.DashboardAnalyticsModule),
      },
      {
        path: 'hri-summary-reports/create',
        loadChildren: () => import('./pages/hir-summary-reports/hir-summary-reports-create-edit/hri-summary-reports-create-edit.module').then(m => m.HirSummaryReportsCreateEditModule)
      },
      {
        path: 'hri-summary-reports/dashboard',
        loadChildren: () => import('./pages/hir-summary-reports/hir-summary-reports.module').then(m => m.HirSummaryReportsModule)
      },
      {
        path: 'unit/child',
        loadChildren: () => import('./pages/organization/organization.module').then(m => m.OrganizationModule)
      },

      {
        path: 'setting/occupation',
        loadChildren: () => import('./pages/occupation/occupation.module').then(m => m.OccupationModule)
      },
      {
        path: 'profile',
        loadChildren: () => import('./pages/user/user.module').then(m => m.UserModule)
      },
      {
        path: 'setting/occupation-ex',
        loadChildren: () => import('./pages/occupation-ex/occupation-ex.module').then(m => m.OccupationExModule)
      },

      {
        path: 'setting/populationGroup',
        loadChildren: () => import('./pages/population-group/population-group.module').then(m => m.PopulationGroupModule)
      },
      {
        path: 'setting/riskbehaviors',
        loadChildren: () => import('./pages/risk-behavior/risk-behavior.mudule').then(m => m.RiskBehaviorModule)
      },

      {
        path: 'setting/ethnics',
        loadChildren: () => import('./pages/ethnics/ethnics.module').then(m => m.EthnicsModule)
      },

      {
        path: 'setting/location',
        loadChildren: () => import('./pages/administrative-unit/administrative-unit.module').then(m => m.AdministrativeUnitModule)
      },
      {
        path: 'setting/hivriskperson',
        loadChildren: () => import('./pages/hivrisk-person/hivrisk-person.module').then(m => m.HIVRiskPersonModule)
      },
      {
        path: '**',
        loadChildren: () => import('./pages/pages/errors/error-404/error-404.module').then(m => m.Error404Module)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    preloadingStrategy: QuicklinkStrategy,
    scrollPositionRestoration: 'enabled',
    relativeLinkResolution: 'corrected',
    anchorScrolling: 'enabled'
  })],
  exports: [RouterModule, QuicklinkModule]
})
export class AppRoutingModule {
}
