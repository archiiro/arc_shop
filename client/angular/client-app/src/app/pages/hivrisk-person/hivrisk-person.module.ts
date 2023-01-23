import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { BreadcrumbsModule } from 'src/@vex/components/breadcrumbs/breadcrumbs.module';
import { PageLayoutModule } from 'src/@vex/components/page-layout/page-layout.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ConfirmDialogModule } from 'src/app/_common/dialogs/confirm-dialog/confirm-dialog.module';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { VexRoutes } from 'src/@vex/interfaces/vex-route.interface';
import { RouterModule } from '@angular/router';
import { QuicklinkModule } from 'ngx-quicklink';
import { NgxPaginationModule } from 'ngx-pagination';
import { GlobitsPaginatorModule } from 'src/app/_common/paginator/paginator.module';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { HIVRiskPersonComponent } from './hivrisk-person.component';
import { HIVRiskPersonCreateUpdateModule } from './hivrisk-person-create-update/hivrisk-person-create-update.module';

const routes: VexRoutes = [
  {
    path: '',
    component: HIVRiskPersonComponent,
    // children: [
    //   { path: '', component: OccupationListComponent },
    //   { path: 'create', component: OccupationCreateEditComponent },
    //   { path: 'edit/:id', component: OccupationCreateEditComponent },
    // ]
  }
];

@NgModule({
  declarations: [HIVRiskPersonComponent],
  imports: [
    CommonModule,
    HIVRiskPersonCreateUpdateModule,
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
        emptyMessage: 'Không có dữ liệu', // Message to show when array is presented, but contains no values
        totalMessage: 'Tổng', // Footer total message
        selectedMessage: 'Chọn' // Footer selected message
      }
    })
  ],
  exports: [RouterModule, QuicklinkModule]
})
export class HIVRiskPersonModule { }
