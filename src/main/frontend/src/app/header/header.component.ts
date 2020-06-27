import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TokenStorage} from '../core/token.storage';
import {Router} from '@angular/router';
import {DialogsService} from "../dialogs/dialogs.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() newCarEvent = new EventEmitter<string>();

  username: string;
  isShowAddCar: boolean;

  constructor(private router: Router, private token: TokenStorage, private dialogsService: DialogsService) {
  }

  ngOnInit(): void {
    this.username = this.token.getUserToken();
    console.warn('nginit of the header');
    console.warn('this.router.url ' + this.router.url);

    if(this.router.url == '/car') {
        this.isShowAddCar = true;
        console.warn('route is /car so show the button for add ');
    }

  }

  logout(): void {
    //TODO need to add /logout invalidate token url to rest endpoints
    //delete the token...
    this.token.signOut();
    this.router.navigate(['/login']);
  }

  home(): void {
    this.router.navigate(['/car/']);
  }

  public openAddNewCarDialog() {
    console.log('openAdd NewCar.. ');

    this.dialogsService.showAddCarDialog().subscribe(
      res => {
        if (res != null) {
          //trigger the update here on response of the dialog onSubmit
          this.newCarEvent.emit('yeahhh new car');
        }
      });
  }



}
