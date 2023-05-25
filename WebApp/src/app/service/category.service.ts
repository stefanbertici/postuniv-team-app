import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../model/category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private categoryApiLink: string = "http://localhost:8080/category";

  constructor(private httpClient: HttpClient) { }

  save(category: Category): Observable<Category> {
    return this.httpClient
      .post<Category>(this.categoryApiLink, category);
  }

  getAll(): Observable<Category[]> {
    return this.httpClient
      .get<Category[]>(this.categoryApiLink);
  }

  delete(categoryId: number): Observable<Category> {
    const categoryRestUrlDelete = `${this.categoryApiLink}/${categoryId}`;
    return this.httpClient.delete<Category>(categoryRestUrlDelete);
  }

  update(category: Category): Observable<Category> {
    const categoryRestUrlUpdate = `${this.categoryApiLink}/${category.id}`;
    return this.httpClient.put<Category>(categoryRestUrlUpdate, category.name);
  }


}
