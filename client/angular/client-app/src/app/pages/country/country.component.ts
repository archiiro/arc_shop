import { AfterViewInit, Component, OnInit, ViewChild, Input } from '@angular/core';
import { Country } from 'src/app/_models/country.model';
import { SearchObject } from 'src/app/_interfaces/search-object';
import { CountryService } from './country.service';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/_common/dialogs/confirm-dialog/confirm-dialog.component';
import { CountryCreateUpdateComponent } from './country-create-update/country-create-update.component';
import { ColumnMode, DatatableComponent } from '@swimlane/ngx-datatable';
import { breadcrumb } from 'src/app/input.const';

@Component({
  selector: 'vex-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.scss']
})
export class CountryComponent implements OnInit, AfterViewInit {
  breadcrumbs: breadcrumb[] = [];
  rows: Country[] = [];
  searchForm: SearchObject | any = null;
  isLoading = false;
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
    private service: CountryService,
    private formBuilder: FormBuilder,
    private dialog: MatDialog
  ) { }

  ngAfterViewInit(): void {
    // throw new Error('Method not implemented.');
  }

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      keyword: null,
      pageIndex: 1,
      pageSize: 10
    })
    this.reloadTable();
    this.breadcrumbs = [
      {link: "/setting/parameter/country", text: "Quốc gia"}
    ]
  }

  submitSearch() {
    this.searchObject.keyword = this.searchForm.value.keyword;
    this.searchObject.pageIndex = 1;
    this.reloadTable();
  }

  onLimitChange(event): void {
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
    this.service.pagingCountrys(this.searchObject)
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
    this.dialog.open(CountryCreateUpdateComponent).afterClosed().subscribe(result => {
      this.reloadTable();
    })
  }

  update(country : Country) {
    this.service.getCountry(country.id)
    .subscribe({
      next: (response) => {
        this.dialog.open(CountryCreateUpdateComponent, {
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
    this.service.deleteCountry(id)
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
        title: "Bạn có chắc chắn muốn xóa quốc gia này",
        text: "Bạn sẽ không thể khôi phục quốc gia này!",
        onYesClick: () => { this.delete(id) }
      }
    });
  }
}
