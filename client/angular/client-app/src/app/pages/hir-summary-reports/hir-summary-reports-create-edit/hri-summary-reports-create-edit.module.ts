import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VexRoutes } from 'src/@vex/interfaces/vex-route.interface';
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
import { PageLayoutModule } from 'src/@vex/components/page-layout/page-layout.module';
import { ConfirmDialogModule } from 'src/app/_common/dialogs/confirm-dialog/confirm-dialog.module';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { NgxPaginationModule } from 'ngx-pagination';
import { GlobitsPaginatorModule } from 'src/app/_common/paginator/paginator.module';
import { HirSummaryReportsCreateEditComponent } from './hir-summary-reports-create-edit.component';
import { SecondaryToolbarModule } from 'src/@vex/components/secondary-toolbar/secondary-toolbar.module';
import { BreadcrumbsModule } from 'src/app/_common/breadcrumbs/breadcrumbs.module';
import { GlobitsInputModule } from 'src/app/_common/form/input/input.module';
import { GlobitsSelectModule } from 'src/app/_common/form/select/select.module';
import { GlobitsDatePickerModule } from 'src/app/_common/form/date-picker/date-picker.module';


const routes: VexRoutes = [
    {
        path: '',
        component: HirSummaryReportsCreateEditComponent,
        children: [
            { path: '', component: HirSummaryReportsCreateEditComponent },
            { path: ':id', component: HirSummaryReportsCreateEditComponent },
        ]
    }
]

@NgModule({
    declarations: [HirSummaryReportsCreateEditComponent],
    imports: [
        CommonModule,
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
        SecondaryToolbarModule,
        GlobitsInputModule,
        GlobitsSelectModule,
        GlobitsDatePickerModule,
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
export class HirSummaryReportsCreateEditModule { }
