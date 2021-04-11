import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Vehicle } from '../models/vehicle.model';
import { WebSocketService } from '../services/websocket.service';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {
  vehicleForm = new FormGroup({
    number: new FormControl(''),
  });

  vehicles:Vehicle[] = [];

  constructor(private http: HttpClient, private wsService: WebSocketService) {
    http.get<Vehicle[]>("/api/vehicles/list").subscribe((response:Vehicle[]) => {
      console.log("response", response);
      this.vehicles = response;
    });
  }

  ngOnInit(): void {
    this.wsService.connect();
    this.wsService.messages$.subscribe((message: any) => {
      console.log(message);
    });
  }

  onSubmit() {
    this.http.post<Vehicle>("/api/vehicles", this.vehicleForm.value).subscribe((response:Vehicle) => {
      console.log("Created vehicle " + response.id + " " + response.number);
    });
  }

  delete(vehicleId:number | undefined):void {
    this.http.delete("/api/vehicles/" + vehicleId).subscribe((response:any) => {
      console.log("Deleted vehicle with id " + vehicleId + " " + response);
    })
  }
}
