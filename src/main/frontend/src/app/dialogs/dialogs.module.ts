import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogsService } from './dialogs.service';
import { FormsModule } from "@angular/forms";
import { AddCarDialogComponent} from "./add-car-dialog/add-car-dialog.component";
import { AddCarOwnerDialogComponent} from "./add-car-owner-dialog/add-car-owner-dialog.component";
import { AddCarReviewDialogComponent} from "./add-car-review-dialog/add-car-review-dialog.component";
import { CustomMaterialModule } from "../core/material.module";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDialogModule} from "@angular/material/dialog";
import {MatIconModule} from "@angular/material/icon";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatToolbarModule} from "@angular/material/toolbar";

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
  declarations: [AddCarDialogComponent, AddCarOwnerDialogComponent, AddCarReviewDialogComponent],
  exports: [AddCarDialogComponent, AddCarOwnerDialogComponent, AddCarReviewDialogComponent],
  entryComponents: [AddCarDialogComponent, AddCarOwnerDialogComponent, AddCarReviewDialogComponent],
  providers: [DialogsService]
})
export class DialogsModule { }
