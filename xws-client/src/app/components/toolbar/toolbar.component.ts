import { ROLE_EDITOR, ROLE_AUTHOR } from './../../config/user-roles-keys';
import { USER_ROLE_KEY, USERNAME_KEY } from './../../config/local-storage-keys';
import { AuthService } from './../../services/auth.service';
import { HOME_PATH, LOGIN_PATH, REGISTRATION_PATH, MY_PUBLICATIONS_PATH, BUSINESS_PROCESSES_PATH, REVIEW_REQUESTS_PATH } from './../../config/router-paths';
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

  onClickRegister(): void {
    this.router.navigate([REGISTRATION_PATH]);
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

  onClickMyPublications(): void {
    this.router.navigate([MY_PUBLICATIONS_PATH]);
  }

  onClickBusinessProcesses(): void {
    this.router.navigate([BUSINESS_PROCESSES_PATH]);
  }

  onClickMyReviewRequests(): void {
    this.router.navigate([REVIEW_REQUESTS_PATH]);
  }

  onClickLogout(): void {
    this.authService.logout();
    this.router.navigate([HOME_PATH]);
  }

}
