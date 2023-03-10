import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContactsEditComponent } from './contacts-edit.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatDividerModule } from '@angular/material/divider';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { ReactiveFormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';


@NgModule({
  declarations: [ContactsEditComponent],
  imports: [
    CommonModule,
    MatDialogModule,
    MatIconModule,
    MatInputModule,
    MatDividerModule,
    MatDatepickerModule,

    ReactiveFormsModule,
    MatMenuModule,
    MatButtonModule,
    MatNativeDateModule
  ]
})
export class ContactsEditModule {
}
