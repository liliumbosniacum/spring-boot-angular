import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VehicleComponent } from './vehicle/vehicle.component';
import { VehicleTypeComponent } from './vehicle/vehicletype/vehicletype.component';

const routes: Routes = [
  { path: 'vehicles', component: VehicleComponent },
  { path: 'vehicletypes', component: VehicleTypeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
