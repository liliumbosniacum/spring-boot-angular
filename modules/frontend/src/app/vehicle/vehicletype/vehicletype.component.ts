import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { PagedResponse } from 'src/app/models/paged-response-model';
import { VehicleType } from 'src/app/models/vehicletype.model';
import { StompService } from 'src/app/services/stomp.service';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicletype.component.html',
  styleUrls: ['./vehicletype.component.css']
})
export class VehicleTypeComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'created', 'modified', 'action'];
  page: number = 0;
  size: number = 4;
  totalCount:number = 0;

  vehicleTypeForm = new FormGroup({
    name: new FormControl(null),
  });

  vehicleTypes:VehicleType[] = [];

  constructor(private http: HttpClient, private stompService: StompService) {}

  ngOnInit(): void {
    this.refreshVehicleTypeTable(this.page, this.size);

    this.stompService.subscribe('/topic/vehicletype', (): void => {
      this.refreshVehicleTypeTable(this.page, this.size);
    });
  }

  onSubmit() {
    this.http.post<VehicleType>("/api/vehicletypes", this.vehicleTypeForm.value).subscribe((response:VehicleType) => {
      if (!!response) {
        console.log("Created vehicle type " + response.id + " " + response.name);
      }
    });
  }

  delete(vehicleId:number | undefined):void {
    this.http.delete("/api/vehicletypes/" + vehicleId).subscribe((response:any) => {
      console.log("Deleted vehicle type with id " + vehicleId + " " + response);
    })
  }

  loadVehicleTypes(event:PageEvent): PageEvent {
    this.page = event.pageIndex;
    this.size = event.pageSize;

    this.refreshVehicleTypeTable(this.page, this.size);
    return event;
  }

  private refreshVehicleTypeTable(page?:number, size?:number): void {
    this.vehicleTypes = [];
    const sufix:string = (page !== undefined && size != undefined)
        ? "?page=" + page + "&size=" + size
        : "";

    this.http.get<PagedResponse<VehicleType>>("/api/vehicletypes/list" + sufix).subscribe((response:PagedResponse<VehicleType>) => {
      this.vehicleTypes = !!response.content ? response.content : [];
      this.totalCount = !!response.totalCount ? response.totalCount : 0;
    });
  }
}
