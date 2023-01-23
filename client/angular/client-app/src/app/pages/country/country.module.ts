import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { QuicklinkModule } from 'ngx-quicklink';
import { PageLayoutModule } from 'src/@vex/components/page-layout/page-layout.module';
// import { BreadcrumbsModule } from 'src/@vex/components/breadcrumbs/breadcrumbs.module';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { VexRoutes } from 'src/@vex/interfaces/vex-route.interface';
import { ConfirmDialogModule } from 'src/app/_common/dialogs/confirm-dialog/confirm-dialog.module';
import { CountryComponent } from './country.component';
import { CountryCreateUpdateModule } from './country-create-update/country-create-update.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { GlobitsPaginatorModule } from 'src/app/_common/paginator/paginator.module';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { BreadcrumbsModule } from 'src/app/_common/breadcrumbs/breadcrumbs.module';

const routes: VexRoutes = [
    {
      path: '',
      component: CountryComponent,
    }
];
@NgModule({
    declarations: [CountryComponent],
    imports: [
        CommonModule,
        ConfirmDialogModule,
        CountryCreateUpdateModule,

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
export class CountryModule { }