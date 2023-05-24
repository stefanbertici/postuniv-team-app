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
  displayedColumns: string[] = ['id', 'name', 'email', 'role'];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.readAll();
  }

  readAll() {
    this.userService.getAll()
      .subscribe(x => this.users = x);
  }

  //Todo: Temporary method, it will be part of a header
  logout(){
    localStorage.removeItem('saloon auth');
    location.reload();
  }

}
