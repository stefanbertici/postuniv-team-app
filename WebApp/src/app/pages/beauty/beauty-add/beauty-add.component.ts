import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Beauty } from 'src/app/model/beauty';
import { BeautyService } from 'src/app/service/beauty.service';

@Component({
  selector: 'app-beauty-add',
  templateUrl: './beauty-add.component.html',
  styleUrls: ['./beauty-add.component.scss']
})
export class BeautyAddComponent {
  constructor(private beautyService: BeautyService, private matDialogRef: MatDialogRef<BeautyAddComponent>) { }
  // {
  //   "name": "string",
  //   "numOfAvailabilityBlocks": 10.00,
  //   "price": 20.00,
  //   "categoryName": "manichiura"
  // }
  saveBeautyService(name: string, numOfAvailabilityBlocks: number, price: number, categoryName: string,) {
    let beautyServiceToBeSaved = { name, numOfAvailabilityBlocks, price, categoryName };

    this.beautyService.save(beautyServiceToBeSaved)
      .subscribe(_ => console.log("Item saved!"))
      console.log(beautyServiceToBeSaved);
      location.reload();
  }


}
