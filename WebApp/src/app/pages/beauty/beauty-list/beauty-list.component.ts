import { Component, OnInit } from '@angular/core';
import { Beauty } from 'src/app/model/beauty';
import { BeautyService } from 'src/app/service/beauty.service';
import { BeautyDetailsComponent } from '../beauty-details/beauty-details.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-beauty-list',
  templateUrl: './beauty-list.component.html',
  styleUrls: ['./beauty-list.component.scss']
})
export class BeautyListComponent implements OnInit {
  beautyServices: Beauty[] = [];
  displayedColumns: string[] = ['category', 'name', 'duration', 'price', 'actions'];

  constructor(private beautyService: BeautyService, private matDialog: MatDialog) { }

  ngOnInit(): void {
    this.readAll();
  }

  readAll() {
    this.beautyService.getAll()
      .subscribe(x => this.beautyServices = x);
  }

  delete(beautyService: Beauty) {
    if (confirm("Are you sure?")) {
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
