import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';
import { CategoryDetailsComponent } from '../category-details/category-details.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {
  categories = new MatTableDataSource<Category>;
  displayedColumns: string[] = ['nr', 'name', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private categoryService: CategoryService, private matDialog: MatDialog) { }

  ngOnInit(): void {
    this.readAll();
  }

  readAll() {
    this.categoryService.getAll()
      .subscribe(x => this.categories.data = x);
  }
  ngAfterViewInit(): void {
    this.categories.paginator = this.paginator;
  }

  delete(categoryId: number) {
    if (confirm("You are about to delete... Are you sure?")) {
      this.categoryService.delete(categoryId)
      .subscribe(_ => {
        console.log("Category saved!")
        location.reload();
      });
    }
  }

  openUpdateComponent(category: Category) {
    this.matDialog.open(CategoryDetailsComponent, {
      data: category,
      height: '170px',
      width: '300px'
    });
  }
}
