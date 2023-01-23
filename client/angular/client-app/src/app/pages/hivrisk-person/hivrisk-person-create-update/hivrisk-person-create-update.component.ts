import { Component, OnInit, Inject } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HIVRiskPerson } from 'src/app/_models/hivriskperson.model';
import { PopulationGroup } from 'src/app/_models/population-group.model';
import { AlertService } from 'src/app/_services/alert.service';
import { PopulationGroupService } from '../../population-group/population-group.service';
import { HIVRiskPersonService } from '../hivrisk-person.sevice';

@Component({
  selector: 'vex-hivrisk-person-create-update',
  templateUrl: './hivrisk-person-create-update.component.html',
  styleUrls: ['./hivrisk-person-create-update.component.scss']
})
export class HIVRiskPersonCreateUpdateComponent implements OnInit {

  id!: string;
  form: UntypedFormGroup;
  isCreateMode!: boolean;
  submitted = false;
  listPopulationGroupDto: PopulationGroup[] = [];


  constructor(@Inject(MAT_DIALOG_DATA) public defaults: any,
    private service: HIVRiskPersonService,
    private dialogRef: MatDialogRef<HIVRiskPersonCreateUpdateComponent>,
    private fb: UntypedFormBuilder,
    private snackBar: MatSnackBar,
    private alertService: AlertService,
    private populationGroupService: PopulationGroupService,
  ) { }

  ngOnInit(): void {
    if (this.defaults) {
      this.isCreateMode = false;
    } else {
      this.isCreateMode = true;
      this.defaults = {} as HIVRiskPerson;
    }

    this.form = this.fb.group({
      id: [this.defaults.id || ''],
      firstName: [this.defaults.firstName || '', [Validators.required]],
      lastName: [this.defaults.lastName || '', [Validators.required]],
      birthDate: [this.defaults.birthDate || ''],
      UIC: [this.defaults.UIC || ''],
      group: [this.defaults.group || '', [Validators.required]],
    })
    this.populationGroupService.getAllPopulationGroup()
      .subscribe(x => {
        this.listPopulationGroupDto = x;
      });
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    console.log('submit')
    if (this.form.invalid)
      return;

    const hivriskperson = this.form.value;

    this.service.saveOrUpdateHIVRiskPerson(hivriskperson)
      .subscribe({
        next: (response) => {
          this.dialogRef.close(response);
          this.alertService.success(this.isCreateMode ? "Create Successful!" : "Update Successful");
        },
        error: (error) => {
          console.log(error.statusText)
          this.alertService.error(this.isCreateMode ? "Create Failed!" : "Update Failed");
        }
      })

  }

}
