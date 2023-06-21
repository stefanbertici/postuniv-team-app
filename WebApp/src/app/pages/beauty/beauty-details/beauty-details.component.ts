import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Beauty } from 'src/app/model/beauty';
import { Category } from 'src/app/model/category';
import { BeautyService } from 'src/app/service/beauty.service';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-beauty-details',
  templateUrl: './beauty-details.component.html',
  styleUrls: ['./beauty-details.component.scss']
})
export class BeautyDetailsComponent implements OnInit{
  beautyServiceSelected: Beauty = this.data;
  categories: Category[] = [];

  ngOnInit(): void {
    this.fetchAvailableCategories();
  }

  constructor(private beautyService: BeautyService, private matDialogRef: MatDialogRef<BeautyDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private categoryService: CategoryService) { }

  updateBeautyService(name: string, numOfAvailabilityBlocks: number, price: number, categoryName: string) {
    let beautyServiceToBeUpdated = { id: this.beautyServiceSelected.id, name, numOfAvailabilityBlocks, price, categoryName };

    this.beautyService.update(beautyServiceToBeUpdated)
      .subscribe(_x => console.log("Item updated"));
      location.reload();
  }
  
  closeModalComponent(){
    this.matDialogRef.close();
  }

  fetchAvailableCategories() {
    return this.categoryService.getAll()
      .subscribe(x => this.categories = x);
  }
}
