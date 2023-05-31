import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['id', 'name', 'email', 'role'];
  users = new MatTableDataSource<User>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.readAll();
  }

  ngAfterViewInit(): void {
    this.users.paginator = this.paginator;
  }

  readAll() {
    this.userService.getAll()
      .subscribe(x => this.users.data = x);
  }

}
