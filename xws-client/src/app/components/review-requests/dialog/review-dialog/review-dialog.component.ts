import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-review-dialog',
  templateUrl: './review-dialog.component.html',
  styleUrls: ['./review-dialog.component.css']
})
export class ReviewDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<ReviewDialogComponent>,
              private toastr: ToastrService) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    if (!this.data.textXml) {
      this.toastr.error('Area can not be empty.');
      return;
    }

    this.dialogRef.close(this.data.textXml);
  }

}
