import { CanActivateFn } from '@angular/router';
import { IdentityService } from './service/identity.service';
import { inject } from '@angular/core';

export const hasRoleGuard: CanActivateFn = (route, state) => {
  const currentService = inject(IdentityService);
  return currentService.loggedUser.role.includes(route.data['role']);
};
