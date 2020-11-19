import {Component, OnInit, ViewChild} from '@angular/core';
import { AccountService } from '../_services/account.service';
import { User } from '../_models';
import { MatMenuTrigger } from '@angular/material/menu';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {



  user: User;
  constructor(private accountService: AccountService) {
    this.accountService.user.subscribe(x => this.user = x);

  }

  ngOnInit(): void {
  }

  logout() {
    this.accountService.logout();
  }
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;

}
