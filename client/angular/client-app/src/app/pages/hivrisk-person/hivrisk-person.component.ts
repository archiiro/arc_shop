import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ColumnMode, DatatableComponent } from '@swimlane/ngx-datatable';
import { ConfirmDialogComponent } from 'src/app/_common/dialogs/confirm-dialog/confirm-dialog.component';
import { SearchObject } from 'src/app/_interfaces/search-object';
import { HIVRiskPerson } from 'src/app/_models/hivriskperson.model';
import { HIVRiskPersonCreateUpdateComponent } from './hivrisk-person-create-update/hivrisk-person-create-update.component';
import { HIVRiskPersonService } from './hivrisk-person.sevice';

@Component({
  selector: 'vex-hivrisk-person',
  templateUrl: './hivrisk-person.component.html',
  styleUrls: ['./hivrisk-person.component.scss']
})
export class HIVRiskPersonComponent implements OnInit {

  rows: HIVRiskPerson[] = [];
  searchForm: SearchObject | any = null;
  isLoading = false;
  totalElements: number = 0;
  totalPages: number = 0;
  pageSizeOptions: number[] = [5, 10, 20, 50];
  searchObject: SearchObject = {
    pageIndex: 1,
    pageSize: 10

  }
  ColumnMode = ColumnMode;
  @ViewChild(DatatableComponent) public table: DatatableComponent;

  @Input()
  columns = [
      { name: 'Họ', prop: 'firstName', visible: true },
      { name: 'Tên', prop: 'lastName', visible: true },
      { name: 'Ngày sinh', prop: 'birthDate', visible: true },
      { name: 'UIC', prop: 'uic', visible: true },
      { name: 'Đối tượng', prop: 'group', visible: true },
  ];

  constructor(
    private service: HIVRiskPersonService,
    private formBuilder: FormBuilder,
    private dialog: MatDialog
  ) { }

  ngAfterViewInit(): void {
    // this.dataSource.paginator = this.paginator;

  }

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      keyword: null,
      pageIndex: 1,
      pageSize: 10
    })
    this.reloadTable();
  }

  compareFn(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
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
    this.service.pagingHIVRiskPerson(this.searchObject)
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
    this.dialog.open(HIVRiskPersonCreateUpdateComponent).afterClosed().subscribe(result => {
      this.reloadTable();
    });
  }

  update(hivriskperson: HIVRiskPerson) {
    this.service.getHIVRiskPerson(hivriskperson.id).subscribe({
      next: (response) => {
        this.dialog.open(HIVRiskPersonCreateUpdateComponent, {
          data: response
        }).afterClosed().subscribe(result => {
          this.reloadTable();
        });
      }, error: (error) => {
        console.log(error);
        this.isLoading = false;
      }
    })
  }

  delete(id: string) {
    this.service.deleteHIVRiskPerson(id)
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
        title: "Bạn có chắc chắn muốn xóa bản ghi này không!",
        text: "Bạn sẽ không thể khôi phục bản ghi này!",
        onYesClick: () => { this.delete(id) }
      }
    });
  }

}
