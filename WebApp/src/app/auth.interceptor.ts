import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { IdentityService } from './service/identity.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private identityService : IdentityService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(this.identityService.token){
      request = request.clone({
        headers: request.headers.set('Authorization', 'Bearer ' + this.identityService.token)
      });
    }
      return next.handle(request);
    }
}

export const AuthInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true,
};
