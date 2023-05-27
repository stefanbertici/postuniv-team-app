import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Beauty } from 'src/app/model/beauty';
import { BeautyService } from 'src/app/service/beauty.service';

@Component({
  selector: 'app-beauty-details',
  templateUrl: './beauty-details.component.html',
  styleUrls: ['./beauty-details.component.scss']
})
export class BeautyDetailsComponent {
  beautyServiceSelected: Beauty = this.data;

  constructor(private beautyService: BeautyService, private matDialogRef: MatDialogRef<BeautyDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  updateBeautyService(name: string, numOfAvailabilityBlocks: number, price: number, categoryName: string) {
    let beautyServiceToBeUpdated = { id: this.beautyServiceSelected.id, name, numOfAvailabilityBlocks, price, categoryName };

    this.beautyService.update(beautyServiceToBeUpdated)
      .subscribe(_x => console.log("Item updated"));
      location.reload();
  }

}
