import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { BeautyService } from 'src/app/service/beauty.service';

@Component({
  selector: 'app-beauty-add',
  templateUrl: './beauty-add.component.html',
  styleUrls: ['./beauty-add.component.scss']
})
export class BeautyAddComponent {
  constructor(private beautyService: BeautyService, private matDialogRef: MatDialogRef<BeautyAddComponent>) { }

 

}
