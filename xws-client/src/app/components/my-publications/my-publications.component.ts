import { ADD_PUBLICATION_PATH } from './../../config/router-paths';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PublicationsService } from './../../services/publications.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { PublicationDialogComponent } from './dialog/publication-dialog/publication-dialog.component';

@Component({
  selector: 'app-my-publications',
  templateUrl: './my-publications.component.html',
  styleUrls: ['./my-publications.component.css']
})
export class MyPublicationsComponent implements OnInit {

  publications: any[] = [];

  constructor(private publicationsService: PublicationsService,
              private toastr: ToastrService,
              private router: Router,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.getPublications();
  }

  private getPublications(): void {
    this.publicationsService.getMyPublications().subscribe(data => {
      this.publications = data;
    }, error => {
      this.toastr.error('There was an error while getting your publications');
    });
  }

  onDeletePublication(publicationId: string): void {
    this.publicationsService.deletePublication(publicationId).subscribe(data => {
      this.toastr.success('Publication has been successfully canceled.');
      this.getPublications();
    }, error => {
      this.toastr.error('Error while canceling publication.');
    });
  }

  onClickAddNewPublication(): void {
    //this.router.navigate([ADD_PUBLICATION_PATH]);
    let dialogRef = this.dialog.open(PublicationDialogComponent, {data: {}});
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.publicationsService.addPublication(result).subscribe(
          (data) => {
            this.getPublications();
          },
          (error) => {
            this.getPublications();
            this.toastr.error('There was an error while adding your publications');
          }
        );
      }
    });
  }
}
