import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BreadcrumbsxComponent } from './breadcrumbs.component';
import { MatIconModule } from '@angular/material/icon';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule
  ],
  declarations: [BreadcrumbsxComponent, BreadcrumbComponent],
  exports: [BreadcrumbsxComponent]
})
export class BreadcrumbsModule {
}
