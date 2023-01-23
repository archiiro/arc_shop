import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VexRoutes } from 'src/@vex/interfaces/vex-route.interface';
import { EthnicsComponent } from './ethnics.component';
//import { OccupationExCreateEditComponent } from './occupation-ex-create-edit/occupation-ex-create-edit.component';
import { RouterModule } from '@angular/router';
import { QuicklinkModule } from 'ngx-quicklink';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { BreadcrumbsModule } from 'src/@vex/components/breadcrumbs/breadcrumbs.module';
import { PageLayoutModule } from 'src/@vex/components/page-layout/page-layout.module';
import { ConfirmDialogModule } from 'src/app/_common/dialogs/confirm-dialog/confirm-dialog.module';
import { EthnicsCreateUpdateModule } from './ethnics-create-update/ethnics-create-update.module';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { NgxPaginationModule } from 'ngx-pagination';
import { GlobitsPaginatorModule } from 'src/app/_common/paginator/paginator.module';


const routes: VexRoutes = [
  {
    path: '',
    component: EthnicsComponent
    // children: [
    //   { path: '', component: EthnicsComponent }
    // ]
  }
]

@NgModule({
  declarations: [
    EthnicsComponent
    ],

  imports: [
    CommonModule,
    EthnicsCreateUpdateModule,
    ConfirmDialogModule,
    //theme
    PageLayoutModule,
    BreadcrumbsModule,
    MatPaginatorModule,
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
export class EthnicsModule { }
