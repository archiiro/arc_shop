import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { GlobitsDatePickerModule } from 'src/app/_common/form/date-picker/date-picker.module';
import { GlobitsInputModule } from 'src/app/_common/form/input/input.module';
import { GlobitsSelectModule } from 'src/app/_common/form/select/select.module';
import { HIVRiskPersonCreateUpdateComponent } from './hivrisk-person-create-update.component';


@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatRadioModule,
    MatSelectModule,
    MatMenuModule,

    MatDividerModule,
    GlobitsInputModule,
    GlobitsSelectModule,
    GlobitsDatePickerModule,
  ],
  declarations: [HIVRiskPersonCreateUpdateComponent],
  exports: [HIVRiskPersonCreateUpdateComponent]
})
export class HIVRiskPersonCreateUpdateModule {
}
