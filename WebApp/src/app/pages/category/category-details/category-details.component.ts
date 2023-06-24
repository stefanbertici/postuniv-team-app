import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.scss']
})
export class CategoryDetailsComponent {
  selectedCategory: Category = this.data;

  constructor(private categoryService: CategoryService,
    private matDialogRef: MatDialogRef<CategoryDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  updateCategory(name: string) {
    let categoryForUpdate: Category = { id: this.selectedCategory.id, name };

    this.categoryService.update(categoryForUpdate)
    .subscribe(_ => {
      console.log("Category updated!")
      location.reload();
    });
  }

  closeModalComponent() {
    this.matDialogRef.close();
  }

}
