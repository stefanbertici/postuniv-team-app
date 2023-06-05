import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
  formGroup!: FormGroup;

  constructor(private userService: UserService, private matDialogRef: MatDialogRef<UserPwdComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  initForm() {
    this.formGroup = new FormGroup({
      pwd: new FormControl('', [Validators.required]),
      pwdConfirm: new FormControl('', [Validators.required])
    })

  }

  changePwd(newPwd: string) {
    this.userService.updatePwd(this.selectedUser.id, newPwd)
      .subscribe({
        next: () => { console.log("Pwd updated!") },
        error: () => {
          alert("Password changed!");
          location.reload();
        }
      });

  }

  closeModalComponent() {
    this.matDialogRef.close();
  }


}
