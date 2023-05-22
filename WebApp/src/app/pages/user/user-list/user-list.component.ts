import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.readAll();
    this.print();
  }

  readAll() {
    this.userService.getAll()
      .subscribe(x => this.users = x);
      console.log(this.users);
  }
  print(){
    console.log(this.users.length);
  }

}
