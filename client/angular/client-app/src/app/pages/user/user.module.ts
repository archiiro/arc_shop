import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VexRoutes } from 'src/@vex/interfaces/vex-route.interface';
import { PageLayoutModule } from 'src/@vex/components/page-layout/page-layout.module';
import { RouterModule } from '@angular/router';
import { QuicklinkModule } from 'ngx-quicklink';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { SecondaryToolbarModule } from 'src/@vex/components/secondary-toolbar/secondary-toolbar.module';
// import { BreadcrumbsModule } from 'src/@vex/components/breadcrumbs/breadcrumbs.module';
import { GlobitsInputModule } from 'src/app/_common/form/input/input.module';
import { BreadcrumbsModule } from 'src/app/_common/breadcrumbs/breadcrumbs.module';
import { UserComponent } from './user.component';


const routes: VexRoutes = [
  {
    path: '',
    children: [
      { path: '', component: UserComponent}
    ]
  }
];

@NgModule({
  declarations: [
    UserComponent],
  imports: [
    CommonModule,
    //theme
    PageLayoutModule,
    BreadcrumbsModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    MatIconModule,
    MatMenuModule,
    FormsModule,
    MatButtonModule,
    MatTooltipModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatProgressBarModule,
    GlobitsInputModule,
    BreadcrumbsModule,
    SecondaryToolbarModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule, QuicklinkModule]
})
export class UserModule { }
