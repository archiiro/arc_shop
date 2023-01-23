import { Component, ContentChild, Input, OnInit, ViewChild } from '@angular/core';
import { ControlContainer, FormControl, FormGroup, FormGroupDirective, UntypedFormBuilder } from '@angular/forms';
import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { MAT_DATE_LOCALE, MAT_DATE_FORMATS } from '@angular/material/core';
import { MatFormFieldControl, MatFormField } from '@angular/material/form-field';
import { DateAdapter } from 'angular-calendar';

export const DATE_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  },
};

@Component({
  selector: 'globits-date-picker',
  templateUrl: './date-picker.component.html',
  styleUrls: ['./date-picker.component.scss'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },
    { provide: MAT_DATE_FORMATS, useValue: DATE_FORMATS },
  ],
  viewProviders: [
    { provide: ControlContainer, useExisting: FormGroupDirective }
  ]
})
export class GlobitsDatePickerComponent implements OnInit {

  @Input() public label: string = '';
  @Input() public hint: string = '';
  @Input() public type: 'text' | 'number' | 'email' | 'password' = 'text';
  @Input() public control: any;
  @Input() public controlName: string = '';
  @Input() public appearance: 'legacy' | 'fill' | 'standard' | 'outline' =
    'outline';
  @Input() public requiredText: string = this.controlName + ' không được để trống';


  formGroup!: FormGroup;
  constructor(private ctrlContainer: FormGroupDirective,
    private formBuilder: UntypedFormBuilder) { }

  @ContentChild(MatFormFieldControl, { static: true })
  public formFieldControl!: MatFormFieldControl<any>;

  @ViewChild('materialFormField', { static: true })
  public matFormField!: MatFormField;

  ngOnInit(): void {
    if (this.matFormField) this.matFormField._control = this.formFieldControl;
    this.formGroup = this.ctrlContainer.form;
    const control = new FormControl('');
    this.formGroup.addControl(this.controlName, control);
  }

}
