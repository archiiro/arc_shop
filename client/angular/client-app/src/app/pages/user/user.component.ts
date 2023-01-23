import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { UserService} from './user.service';
import { User } from 'src/app/_models/user.model';
import { FormBuilder, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

interface breadcrumb {
  link: string;
  text: string;
}
@Component({
  selector: 'vex-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit, AfterViewInit{
  breadcrumbs: breadcrumb[] = [];
  form: UntypedFormGroup;

  constructor(
    private service: UserService,
    private formBuilder: FormBuilder,
    private fb: UntypedFormBuilder,
    ) { }

  ngAfterViewInit(): void {
    // throw new Error('Method not implemented.');
  }

  ngOnInit(): void {
    this.breadcrumbs = [
      { link: "/profile", text: "Thông tin cá nhân" }
    ];
    this.initForm();
  }
  initForm() {
    this.form = this.fb.group({
      id: '',
      code: ['', [Validators.required]],
      personName: ['', [Validators.required]],
    });
  }
  get f() { return this.form.controls; }

}
