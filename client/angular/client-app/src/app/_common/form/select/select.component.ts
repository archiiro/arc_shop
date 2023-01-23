import { Component, ContentChild, Input, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormGroupDirective, UntypedFormBuilder, FormControl, ControlContainer } from '@angular/forms';
import { MatFormFieldControl, MatFormField } from '@angular/material/form-field';

@Component({
  selector: 'globits-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss'],
  viewProviders: [
    { provide: ControlContainer, useExisting: FormGroupDirective }
  ]
})
export class GlobitsSelectComponent implements OnInit {

  @Input() public options: any[] = [];
  @Input() public objectValue: boolean = false;
  @Input() public placeholder: string = '';
  @Input() public multiple: boolean = false;
  @Input() public label: string = '';
  @Input() public hint: string = '';
  @Input() public type: 'text' | 'number' | 'email' | 'password' = 'text';
  @Input() public control: any;
  @Input() public controlName: string = '';
  @Input() public appearance: 'legacy' | 'fill' | 'standard' | 'outline' =
    'outline';
  @Input() public requiredText: string = this.controlName + ' không được để trống';

  formGroup!: FormGroup;
  constructor(
    private ctrlContainer: FormGroupDirective,
    private formBuilder: UntypedFormBuilder
  ) { }

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

  compareFn(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id == c2.id : c1 == c2;
  }

}
