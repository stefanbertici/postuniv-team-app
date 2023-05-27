import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Beauty } from '../model/beauty';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BeautyService {
  private beautyServicesApiLink: string = "http://localhost:8080/beauty-service";

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Beauty[]> {
    const beautyServicesApiLinkFetch: string = "http://localhost:8080/beauty-service/readAllBeautyServices";
    return this.httpClient
      .get<Beauty[]>(beautyServicesApiLinkFetch);
  }

  //Todo: Any because different field values: To be reported
  save(beautyService: any): Observable<any> {
    const beautyServicesSaveApiLink = "http://localhost:8080/beauty-service/save";
    return this.httpClient
      .post<any>(beautyServicesSaveApiLink, beautyService);
  }

  delete(beautyServiceId: number): Observable<Beauty> {
    const beautyRestUrlDelete = `${this.beautyServicesApiLink}/${beautyServiceId}`;
    return this.httpClient
      .delete<Beauty>(beautyRestUrlDelete);
  }

  //Todo: Any because different field values: To be reported
  update(beautyService: any): Observable<any> {
    const beautyServiceRestUrlUpdate = `${this.beautyServicesApiLink}/${beautyService.id}`;
    
    return this.httpClient
      .put<any>(beautyServiceRestUrlUpdate, beautyService);
  }
  
}
