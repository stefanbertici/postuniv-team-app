import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';
import { CategoryDetailsComponent } from '../category-details/category-details.component';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {
  categories: Category[] = [];
  displayedColumns: string[] = ['nr', 'name', 'actions'];

  constructor(private categoryService: CategoryService, private matDialog: MatDialog) { }

  ngOnInit(): void {
    this.readAll();
  }

  readAll() {
    this.categoryService.getAll()
      .subscribe(x => this.categories = x);
  }

  delete(categoryId: number) {
    if (confirm("Are you sure?")) {
      this.categoryService.delete(categoryId)
        .subscribe(_ => console.log("Category item deleted!"));
    }
    location.reload();
  }

  openUpdateComponent(category: Category) {
    this.matDialog.open(CategoryDetailsComponent, {
      data: category,
      height: '170px',
      width: '300px'
    });
  }
}
