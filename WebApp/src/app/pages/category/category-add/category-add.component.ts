import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.scss']
})
export class CategoryAddComponent {
  constructor(private categoryService: CategoryService, private matDialogRef: MatDialogRef<CategoryAddComponent>) { }

  saveCategory(categoryName: string) {
    let category: Category = { name: categoryName };

    this.categoryService.save(category)
      .subscribe(_ => {
        console.log("Category saved!")
        location.reload();
      });

  }

  closeModalComponent() {
    this.matDialogRef.close();
  }
}
