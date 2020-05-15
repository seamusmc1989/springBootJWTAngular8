import { BrowserModule } from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {CustomMaterialModule} from './core/material.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import {UserComponent} from './user/user.component';
import {AppRoutingModule} from './core/app.routing.module';
import {LoginComponent} from './login/login.component';
import {ErrorDialogComponent} from './core/error-dialog.component';
import {UserService} from "./app.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthService} from "./core/auth.service";
import {Inteceptor} from "./core/inteceptor";
import {TokenStorage} from "./core/token.storage";
import { DialogsModule } from './dialogs/dialogs.module';
import {CarService} from "./car/car.service";
import {CarComponent} from "./car/car.component";
import {HeaderComponent} from "./header/header.component";
import {CarDetailService} from "./car-detail/car-detail.service";
import {CarDetailComponent} from "./car-detail/car-detail.component";
import {OwnerService} from "./dialogs/add-car-owner-dialog/owner.service";
import {
  MatButtonModule,
  MatDialogModule,
  MatFormFieldModule, MatIconModule,
  MatInputModule, MatProgressBarModule,
  MatToolbarModule
} from "@angular/material";
import {FlexLayoutModule} from "@angular/flex-layout";
import {Child1Component} from "./child1/child1.component";


@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],

  declarations: [
    AppComponent,
    HeaderComponent,
    UserComponent,
    LoginComponent,
    ErrorDialogComponent,
    CarComponent,
    CarDetailComponent,
    Child1Component
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    FormsModule,
    AppRoutingModule,
    DialogsModule,
    FlexLayoutModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatProgressBarModule,
    MatToolbarModule
  ],
  entryComponents: [ErrorDialogComponent],
  providers: [ErrorDialogComponent, UserService, AuthService, TokenStorage,
    CarService, CarDetailService, OwnerService,
    {provide: HTTP_INTERCEPTORS,
    useClass: Inteceptor,
    multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
