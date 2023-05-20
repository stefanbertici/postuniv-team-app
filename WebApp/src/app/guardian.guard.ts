import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { IdentityService } from './service/identity.service';

export const guardianGuard: CanActivateFn = (route, state) => {
  const currentService = inject(IdentityService);
  return currentService.isLoggedIn$;
};
