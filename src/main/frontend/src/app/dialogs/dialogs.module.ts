import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogsService } from './dialogs.service';
import {
  MatButtonModule, MatDialogModule, MatFormFieldModule, MatIcon, MatIconModule, MatInputModule,
  MatProgressBarModule, MatToolbarModule
} from '@angular/material';
import { FormsModule } from "@angular/forms";
import { AddCarDialogComponent} from "./add-car-dialog/add-car-dialog.component";
import { AddCarOwnerDialogComponent} from "./add-car-owner-dialog/add-car-owner-dialog.component";
import { CustomMaterialModule } from "../core/material.module";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatProgressBarModule,
    MatToolbarModule,
    CustomMaterialModule,
  ],
  declarations: [AddCarDialogComponent, AddCarOwnerDialogComponent],
  exports: [AddCarDialogComponent, AddCarOwnerDialogComponent],
  entryComponents: [AddCarDialogComponent, AddCarOwnerDialogComponent],
  providers: [DialogsService]
})
export class DialogsModule { }
