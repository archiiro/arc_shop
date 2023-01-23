import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ColumnMode, DatatableComponent } from '@swimlane/ngx-datatable';
import { SearchObject } from 'src/app/_interfaces/search-object';
import { HriSummaryReports } from 'src/app/_models/hri-summary-reports.model';
import { HirSummaryReportsService } from './hir-summary-reports.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmDialogComponent } from 'src/app/_common/dialogs/confirm-dialog/confirm-dialog.component';
import { breadcrumb } from 'src/app/input.const';

@Component({
  selector: 'vex-hir-summary-reports',
  templateUrl: './hir-summary-reports.component.html',
  styleUrls: ['./hir-summary-reports.component.scss']
})
export class HirSummaryReportsComponent implements OnInit {
  isCreateMode!: boolean;
  rows: HriSummaryReports[] = [];
  rowSelected: HriSummaryReports = new HriSummaryReports();
  searchForm: SearchObject | any = null;
  isLoading = false;
  totalElements: number = 0;
  totalPages: number = 0;
  pageSizeOptions: number[] = [5, 10, 20, 50];
  searchObject: SearchObject = {
    pageIndex: 1,
    pageSize: 10

  }
  id: string = null;
  breadcrumbs: breadcrumb[] = [];


  ColumnMode = ColumnMode;
  @ViewChild(DatatableComponent) public table: DatatableComponent;
  @Input()
  columns = [
    { name: 'Ngày báo cáo', prop: 'reportDate', visible: true },
    { name: 'Quý', prop: 'quarter', visible: true },
    { name: 'Năm', prop: 'year', visible: true },
  ];

  constructor(
    private service: HirSummaryReportsService,
    private formBuilder: FormBuilder,
    private dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.isCreateMode = !this.id;
    this.breadcrumbs = [
      {link: null, text: 'Danh sách báo cáo ' }
    ]
    this.initForm();
  }

  initForm() {
    this.searchForm = this.formBuilder.group({
      keyword: null,
      pageIndex: 1,
      pageSize: 10
    })
    this.reloadTable();
  }
  reloadTable() {
    this.isLoading = true;
    this.service.pagingHriSummaryReports(this.searchObject)
      .subscribe({
        next: (response) => {
          this.rows = response?.content;
          this.totalElements = response?.totalElements;
          this.totalPages = response.totalPages;
          this.isLoading = false;
        },
        error: (error) => {
          console.log(error);
          this.isLoading = false;
        }
      })
  }

  submitSearch() {
    this.searchObject.keyword = this.searchForm.value.keyword;
    this.searchObject.pageIndex = 1;
    this.reloadTable();
  }

  public onLimitChange(event): void {
    this.searchObject.pageSize = parseInt(event.target.value, 10);
    this.searchObject.pageIndex = 1;
    this.reloadTable();
  }

  setPage(pageInfo) {
    if (pageInfo.offset >= 0) {
      this.searchObject.pageIndex = pageInfo.offset + 1;
      this.reloadTable();
    }
  }
  get f() { return this.searchForm.controls; }

  get visibleColumns() {
    return this.columns.filter(column => column.visible);
  }

  toggleColumnVisibility(column, event) {
    event.stopPropagation();
    event.stopImmediatePropagation();
    column.visible = !column.visible;
  }

  delete(id: string) {
    this.service.deleteHirSummaryReports(id)
      .subscribe({
        next: () => {
          this.reloadTable();
          this.dialog.closeAll();
        },
        error: (error) => {
          console.log(error);
        }
      })
  }

  handleDelete(id: string) {
    this.dialog.open(ConfirmDialogComponent, {
      disableClose: false,
      width: '400px',
      data: {
        title: "Bạn có chắc chắn muốn xóa bản ghi này",
        text: "Bạn sẽ không thể khôi phục bản ghi này!",
        onYesClick: () => { this.delete(id) }
      }
    });
  }




}
