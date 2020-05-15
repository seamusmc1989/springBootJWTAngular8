import { Injectable } from '@angular/core';


const TOKEN_KEY = 'AuthToken';
const TOKEN_USER_KEY = 'AuthUserToken';
const USER_ROLES_KEY = 'UserRolesKey';
const USER_ID_KEY = 'UserIdKey';

@Injectable()
export class TokenStorage {

  constructor() { }

  signOut() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }
  
  public saveUserToken(userToken: string) {
    window.sessionStorage.removeItem(TOKEN_USER_KEY);
    window.sessionStorage.setItem(TOKEN_USER_KEY, userToken);
  }

  public getUserToken(): string {
    return sessionStorage.getItem(TOKEN_USER_KEY);
  }

  public saveUserRoles(userRoles: any) {
    window.sessionStorage.removeItem(USER_ROLES_KEY);
    window.sessionStorage.setItem(USER_ROLES_KEY, userRoles);
  }

  public getUserRoles(): string {
    return sessionStorage.getItem(USER_ROLES_KEY);
  }

  public saveUserId(userId: string) {
    window.sessionStorage.removeItem(USER_ID_KEY);
    window.sessionStorage.setItem(USER_ID_KEY, userId);
  }

  public getUserId(): string {
    return sessionStorage.getItem(USER_ID_KEY);
  }
  
}
