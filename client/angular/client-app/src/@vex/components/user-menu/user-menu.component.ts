import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { PopoverRef } from '../popover/popover-ref';

@Component({
  selector: 'vex-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss']
})
export class UserMenuComponent implements OnInit {
  constructor(
    private readonly popoverRef: PopoverRef,
    private authService: AuthService) { }

  ngOnInit(): void {

  }

  close(): void {
    setTimeout(() => this.popoverRef.close(), 250);
  }

  logout(): void {
    this.close();
    this.authService.logout();
  }
}
