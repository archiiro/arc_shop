import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

interface breadcrumb {
  link: string;
  text: string;
}
@Component({
  selector: 'vex-breadcrumbs',
  template: `
    <div class="flex items-center">
      <vex-breadcrumb>
        <a [routerLink]="['/']">
          <mat-icon svgIcon="mat:home" class="icon-sm"></mat-icon>
        </a>
      </vex-breadcrumb>
      <ng-container *ngFor="let crumb of crumbs">
        <div class="w-1 h-1 bg-gray rounded-full ltr:mr-2 rtl:ml-2"></div>
        <vex-breadcrumb>
          <a [routerLink]=[] (click)="handleClickCrumb(crumb.link)">{{ crumb.text }}</a>
        </vex-breadcrumb>
      </ng-container>
    </div>
  `
})
export class BreadcrumbsxComponent implements OnInit {

  @Input() crumbs: breadcrumb[] = [];


  constructor(private router: Router,) {
  }

  ngOnInit() {
  }

  handleClickCrumb(link: string){
    if(link != null){
      this.router.navigate(["setting/location/" + link]);
    }
  }

}
