import { Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-pwd',
  templateUrl: './user-pwd.component.html',
  styleUrls: ['./user-pwd.component.scss']
})
export class UserPwdComponent implements OnInit {
  selectedUser: User = this.data;
  formGroup!: FormGroup;

  constructor(private userService: UserService, private matDialogRef: MatDialogRef<UserPwdComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.formGroup = new FormGroup({
      pwd: new FormControl('', [Validators.required, Validators.minLength(6)]),
      pwdConfirm: new FormControl('', [Validators.required])
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const password = control.get('pwd');
    const confirmPassword = control.get('pwdConfirm');

    if (password?.value !== confirmPassword?.value) {
      return { 'passwordMismatch': true };
    }

    return null;
  }

  changePwd() {
    if (this.formGroup.valid) {
      this.userService.updatePwd(this.selectedUser.id, this.formGroup.controls['pwd'].value)
        .subscribe({
          next: () => { console.log("Pwd updated!") },
          error: () => {
            alert("Password changed!");
            location.reload();
          }
        });
    }
  }

  closeModalComponent() {
    this.matDialogRef.close();
  }

}
