import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { FormControl, UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { breadcrumb, listQuarter, listYear } from 'src/app/input.const';
import { HriSummaryReports } from 'src/app/_models/hri-summary-reports.model';
import { AlertService } from 'src/app/_services/alert.service';
import { HirSummaryReportsService } from '../hir-summary-reports.service';

@Component({
  selector: 'vex-hir-summary-reports-create-edit',
  templateUrl: './hir-summary-reports-create-edit.component.html',
  styleUrls: ['./hir-summary-reports-create-edit.component.scss']
})
export class HirSummaryReportsCreateEditComponent implements OnInit {
  id!: string;
  submitted = false;
  form: UntypedFormGroup;
  isCreateMode!: boolean;
  hirSummaryReports: HriSummaryReports;
  breadcrumbs: breadcrumb[] = [];
  listQuarter = listQuarter;
  listYear = listYear;

  @ViewChild('hdrTpl') hdrTpl: TemplateRef<any>;
  constructor(
    private service: HirSummaryReportsService,
    private fb: UntypedFormBuilder,
    private alertService: AlertService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.isCreateMode = !this.id;
    this.breadcrumbs = [
      {link: null, text: this.isCreateMode ? 'Nhập báo cáo ' : 'Chỉnh sửa báo cáo' }
    ]
    this.initForm();
  }

  initForm() {
    this.form = this.fb.group({
      id: '',
      reportDate: '',
      year: null,
      quarter: null,
      title: '',
      populationGroups: new FormControl([])
    })
    this.service.createHriSummaryReports(null)
    .subscribe({
      next: (response => {
        this.hirSummaryReports = response;
      }),
      error: (error) => {
        console.log(error);
      }
    })
  }

  get f() { return this.form.controls; }
  onSubmit() {
    console.log("Submit");
    this.submitted = true;
    if (this.form.invalid)
      return;
    const hirSummaryReport = this.form.value;
    console.log(hirSummaryReport);
    this.service.saveHirSummaryReports(hirSummaryReport)
      .subscribe({
        next: (response) => {
          this.alertService.success(this.isCreateMode ? "Thêm mới thành công!" : "Cập nhật thành công");
          this.router.navigate(["/hri-summary-reports/dashboard"]);
        },
        error: (error) => {
          this.alertService.error(this.isCreateMode ? "Thêm mới thất bại!" : "Cập nhật thất bại");
        }
      })
  }
}
