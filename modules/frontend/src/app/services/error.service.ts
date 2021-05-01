import { Injectable, OnInit } from "@angular/core";
import { StompService } from "./stomp.service";
import {MatDialog} from '@angular/material/dialog';
import { ErrorDialogComponent } from "../error/error-dialog.component";

@Injectable({
    providedIn: 'root',
})
export class ErrorService {

    constructor(private stompService: StompService, public dialog: MatDialog) {}

    public subscribeToErrors() : void {
        this.stompService.subscribe('/topic/error', (response: any): void => {
            this.dialog.open(ErrorDialogComponent, {
                data: {
                  errorMessage: response.body
                }
              });
        });
    }
}