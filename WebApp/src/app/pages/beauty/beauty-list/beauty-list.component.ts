import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Beauty } from 'src/app/model/beauty';
import { BeautyService } from 'src/app/service/beauty.service';
import { BeautyDetailsComponent } from '../beauty-details/beauty-details.component';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-beauty-list',
  templateUrl: './beauty-list.component.html',
  styleUrls: ['./beauty-list.component.scss']
})
export class BeautyListComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['category', 'name', 'duration', 'price', 'actions'];
  beautyServices = new MatTableDataSource<Beauty>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private beautyService: BeautyService, private matDialog: MatDialog) { }

  ngOnInit(): void {
    this.readAll();
  }

  ngAfterViewInit(): void {
    this.beautyServices.paginator = this.paginator;
  }

  readAll() {
    this.beautyService.getAll()
      .subscribe(x => this.beautyServices.data = x);
  }

  delete(beautyService: Beauty) {
    if (confirm("You are about to delete... Are you sure?")) {
      this.beautyService.delete(beautyService.id!)
        .subscribe(_ => console.log("Item deleted!"));
      location.reload();
    }
  }

  openUpdateServiceComponent(beautyService: Beauty) {
    this.matDialog.open(BeautyDetailsComponent, {
      data: beautyService,
      height: '350px',
      width: '300px'
    });
  }

}
