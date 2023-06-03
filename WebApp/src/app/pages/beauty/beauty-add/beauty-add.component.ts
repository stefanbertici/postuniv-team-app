import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Beauty } from 'src/app/model/beauty';
import { Category } from 'src/app/model/category';
import { BeautyService } from 'src/app/service/beauty.service';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-beauty-add',
  templateUrl: './beauty-add.component.html',
  styleUrls: ['./beauty-add.component.scss']
})
export class BeautyAddComponent implements OnInit{
  availableCategories: Category[] = [];

  constructor(private beautyService: BeautyService, 
    private matDialogRef: MatDialogRef<BeautyAddComponent>,
    private categoryService: CategoryService) { }

ngOnInit(): void {
  this.fetchAvailableServices();
}

  saveBeautyService(name: string, numOfAvailabilityBlocks: number, price: number, categoryName: string,) {
    let beautyServiceToBeSaved = { name, numOfAvailabilityBlocks, price, categoryName };

    this.beautyService.save(beautyServiceToBeSaved)
      .subscribe(_ => console.log("Item saved!"))
    location.reload();
  }

  fetchAvailableServices() {
    return this.categoryService.getAll()
      .subscribe(x => this.availableCategories = x);
  }

  closeModalComponent(){
    this.matDialogRef.close();
  }
}
