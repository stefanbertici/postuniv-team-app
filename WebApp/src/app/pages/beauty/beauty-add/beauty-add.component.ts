import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Beauty } from 'src/app/model/beauty';
import { Category } from 'src/app/model/category';
import { TimeUnit } from 'src/app/model/timeunit';
import { BeautyService } from 'src/app/service/beauty.service';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-beauty-add',
  templateUrl: './beauty-add.component.html',
  styleUrls: ['./beauty-add.component.scss']
})
export class BeautyAddComponent implements OnInit {
  availableCategories: Category[] = [];
  timeUnits: TimeUnit[] = [
    { id: 1, displayValue: '30m', equivalentValue: 1 },
    { id: 2, displayValue: '1h', equivalentValue: 2 },
    { id: 3, displayValue: '1h30m', equivalentValue: 3 },
    { id: 4, displayValue: '2h', equivalentValue: 4 },
    { id: 5, displayValue: '2h30m', equivalentValue: 5 },
    { id: 6, displayValue: '3h', equivalentValue: 6 },
    { id: 7, displayValue: '3h30m', equivalentValue: 7 },
    { id: 8, displayValue: '4h', equivalentValue: 8 },
  ];

  constructor(private beautyService: BeautyService,
    private matDialogRef: MatDialogRef<BeautyAddComponent>,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.fetchAvailableServices();
  }

  saveBeautyService(name: string, numOfAvailabilityBlocks: number, price: number, categoryName: string,) {
    let beautyServiceToBeSaved = { name, numOfAvailabilityBlocks, price, categoryName };

    this.beautyService.save(beautyServiceToBeSaved)
      .subscribe(_ => {
        console.log("Item saved!");
        location.reload();
      });

  }

  fetchAvailableServices() {
    return this.categoryService.getAll()
      .subscribe(x => this.availableCategories = x);
  }

  closeModalComponent() {
    this.matDialogRef.close();
  }
}
