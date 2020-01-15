import { ROLE_EDITOR, ROLE_AUTHOR } from './../../config/user-roles-keys';
import { USER_ROLE_KEY, USERNAME_KEY } from './../../config/local-storage-keys';
import { AuthService } from './../../services/auth.service';
import { HOME_PATH, LOGIN_PATH } from './../../config/router-paths';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  constructor(private router: Router,
              private authService: AuthService, private t: ToastrService) { 
  }

  ngOnInit() {
  }

  isUserLoggedIn(): boolean {
    return this.authService.isUserLoggedIn();
  }

  onTitleClick(): void {
    this.router.navigate([HOME_PATH]);
  }

  onClickLogin(): void {
    this.router.navigate([LOGIN_PATH]);
  }

  getUsername(): string {
    return localStorage.getItem(USERNAME_KEY);
  }

  isEditorLoggedIn(): boolean {
    return localStorage.getItem(USER_ROLE_KEY) === ROLE_EDITOR;
  }

  isAuthorLoggedIn(): boolean {
    return localStorage.getItem(USER_ROLE_KEY) === ROLE_AUTHOR;
  }

  onClickLogout(): void {
    this.authService.logout();
    this.router.navigate([HOME_PATH]);
  }

}
