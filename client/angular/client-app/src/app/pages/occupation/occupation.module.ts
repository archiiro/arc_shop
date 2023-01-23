import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OccupationComponent } from './occupation.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
// import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { BreadcrumbsModule } from 'src/@vex/components/breadcrumbs/breadcrumbs.module';
import { PageLayoutModule } from 'src/@vex/components/page-layout/page-layout.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { OccupationCreateUpdateModule } from './occupation-create-update/occupation-create-update.module';
import { ConfirmDialogModule } from 'src/app/_common/dialogs/confirm-dialog/confirm-dialog.module';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { VexRoutes } from 'src/@vex/interfaces/vex-route.interface';
import { RouterModule } from '@angular/router';
import { QuicklinkModule } from 'ngx-quicklink';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { NgxPaginationModule } from 'ngx-pagination';
import { GlobitsPaginatorModule } from 'src/app/_common/paginator/paginator.module';

const routes: VexRoutes = [
  {
    path: '',
    component: OccupationComponent,
    // children: [
    //   { path: '', component: OccupationListComponent },
    //   { path: 'create', component: OccupationCreateEditComponent },
    //   { path: 'edit/:id', component: OccupationCreateEditComponent },
    // ]
  }
];

@NgModule({
  declarations: [OccupationComponent],
  imports: [
    CommonModule,
    OccupationCreateUpdateModule,
    ConfirmDialogModule,
    //theme
    PageLayoutModule,
    BreadcrumbsModule,
    // MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    MatCheckboxModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    FormsModule,
    MatTooltipModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatButtonToggleModule,
    MatProgressBarModule,
    NgxPaginationModule,
    GlobitsPaginatorModule,
    RouterModule.forChild(routes),
    NgxDatatableModule.forRoot({
      messages: {
        emptyMessage: 'No data to display', // Message to show when array is presented, but contains no values
        totalMessage: 'total', // Footer total message
        selectedMessage: 'selected' // Footer selected message
      }
    })
  ],
  exports: [RouterModule, QuicklinkModule]
})
export class OccupationModule { }
