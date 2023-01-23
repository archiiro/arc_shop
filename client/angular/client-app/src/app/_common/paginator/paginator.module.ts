import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GlobitsPaginatorComponent } from './paginator.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [GlobitsPaginatorComponent],
  exports: [GlobitsPaginatorComponent]
})

export class GlobitsPaginatorModule {

}
