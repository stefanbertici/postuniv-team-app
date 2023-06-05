import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-pwd',
  templateUrl: './user-pwd.component.html',
  styleUrls: ['./user-pwd.component.scss']
})
export class UserPwdComponent {
  selectedUser: User = this.data;

  constructor(private userService: UserService, private matDialogRef: MatDialogRef<UserPwdComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  changePwd(newPwd: string) {
    this.userService.updatePwd(this.selectedUser.id, newPwd)
      .subscribe(_ => console.log("Pwd updated!"));
  }

  closeModalComponent() {
    this.matDialogRef.close();
  }


}
