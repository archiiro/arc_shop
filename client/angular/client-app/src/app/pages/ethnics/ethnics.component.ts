import { AfterViewInit, Component, OnInit, ViewChild, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/_common/dialogs/confirm-dialog/confirm-dialog.component';
import { EthnicsCreateUpdateComponent } from './ethnics-create-update/ethnics-create-update.component';
import { Ethnics } from 'src/app/_models/ethnics.model';
import { EthnicsService } from './ethnics.service';
import { SearchObject } from 'src/app/_interfaces/search-object';
import { ColumnMode, DatatableComponent } from '@swimlane/ngx-datatable';


@Component({
  selector: 'app-ethnics',
  templateUrl: './ethnics.component.html',
  styleUrls: ['./ethnics.component.scss']
})
export class EthnicsComponent implements OnInit,AfterViewInit {
  rows: Ethnics[] = [];
  isLoading = false;
  searchForm: SearchObject | any = null;
  totalElements: number = 0;
  totalPages: number = 0;
  searchObject: SearchObject = {
    pageIndex: 1,
    pageSize: 10
  }

  ColumnMode = ColumnMode;
  @ViewChild(DatatableComponent) public table: DatatableComponent;

  @Input()
  columns = [
    { name: 'Mã', prop: 'code', visible: true },
    { name: 'Tên', prop: 'name', visible: true },
    { name: 'Mô tả', prop: 'description', visible: true }
  ];

  constructor(
    private service: EthnicsService,
    private formBuilder: FormBuilder,
    private dialog: MatDialog
  ) { }

  ngAfterViewInit(): void {
  }

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      keyword: null,
      pageIndex: 1,
      pageSize: 10
    })
    this.reloadTable();
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
  get visibleColumns() {
    return this.columns.filter(column => column.visible);
  }

  toggleColumnVisibility(column, event) {
    event.stopPropagation();
    event.stopImmediatePropagation();
    column.visible = !column.visible;
  }
  reloadTable() {
    this.isLoading = true;
    this.service.pagingEthnics(this.searchObject)
      .subscribe({
        next: (response) => {
          this.rows = response?.content;
          this.totalElements = response?.totalElements;
          this.totalPages = response?.totalPages;
          this.isLoading = false;
        },
        error: (error) => {
          console.log(error);
          this.isLoading = false;
        }
      })
  }

  create() {
    this.dialog.open(EthnicsCreateUpdateComponent).afterClosed().subscribe(result => {
      this.reloadTable();
    });
  }

  update(ethnics: Ethnics) {
    this.service.getEthnics(ethnics.id)
      .subscribe({
        next: (response) => {
          this.dialog.open(EthnicsCreateUpdateComponent, {
            data: response
          }).afterClosed().subscribe(result => {
            this.reloadTable();
          });
        },error: (error) => {
          console.log(error);
          this.isLoading = false;
        }
      })

  }


  delete(id: string) {
    this.service.deleteEthnics(id)
      .subscribe({
        next: () => {
          this.reloadTable();
          this.dialog.closeAll();
        },
        error: (error) => {
          console.log(error)
        }
      })
  }

  handleDelete(id: string) {
    this.dialog.open(ConfirmDialogComponent, {
      disableClose: false,
      width: '400px',
      data: {
        title: "Bạn có chắc muốn xoá dân tộc này không ?",
        text: "Bạn sẽ không khôi phục lại được bản ghi này!",
        onYesClick: () => { this.delete(id) }
      }
    });
  }
}
