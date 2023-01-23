import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

interface breadcrumb {
  link: string;
  text: string;
}
@Component({
  selector: 'globits-breadcrumbs',
  template: `
    <div class="flex items-center">
      <globits-breadcrumb>
        <a [routerLink]="['/']">
          <mat-icon svgIcon="mat:home" class="icon-sm"></mat-icon>
        </a>
      </globits-breadcrumb>
      <ng-container>
      <globits-breadcrumb>
          <a [routerLink]="['/']">Trang chủ</a>
        </globits-breadcrumb>
      </ng-container>
      <ng-container *ngFor="let crumb of crumbs">
        <div class="w-1 h-1 bg-gray rounded-full ltr:mr-2 rtl:ml-2"></div>
        <globits-breadcrumb>
          <a [routerLink]="[]"(click)="handleClickCrumb(crumb.link)">{{ crumb.text }}</a>
        </globits-breadcrumb>
      </ng-container>
    </div>
  `
})
export class BreadcrumbsComponent implements OnInit {

  @Input() crumbs: breadcrumb[] = [];

  constructor(private router: Router,) {
  }
  handleClickCrumb(link: string){
    if(link != null){
      this.router.navigate([link]);
    }
  }
  ngOnInit() {
  }
}
