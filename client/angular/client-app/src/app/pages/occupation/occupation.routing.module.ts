import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { QuicklinkModule } from 'ngx-quicklink';
import { VexRoutes } from 'src/@vex/interfaces/vex-route.interface';
import { OccupationComponent } from './occupation.component';

const routes: VexRoutes = [
    {
        path: '',
        component: OccupationComponent,
        // children: [
        //   { path: '', component: OccupationListComponent },
        //   { path: 'create', component: OccupationCreateEditComponent },
        //   { path: 'edit/:id', component: OccupationCreateEditComponent },
        // ]
      }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule, QuicklinkModule]
})
export class OccupationRoutingModule {
}
