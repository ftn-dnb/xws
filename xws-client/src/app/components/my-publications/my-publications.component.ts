import { ADD_PUBLICATION_PATH } from './../../config/router-paths';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PublicationsService } from './../../services/publications.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { PublicationDialogComponent } from './dialog/publication-dialog/publication-dialog.component';
import { BusinessProcessService } from 'src/app/services/business-process.service';

@Component({
  selector: 'app-my-publications',
  templateUrl: './my-publications.component.html',
  styleUrls: ['./my-publications.component.css']
})
export class MyPublicationsComponent implements OnInit {

  publications: any[] = [];

  constructor(private publicationsService: PublicationsService,
              private processService: BusinessProcessService,
              private toastr: ToastrService,
              private router: Router,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.getPublications();
  }

  private getPublications(): void {
    this.publicationsService.getMyPublications().subscribe(data => {
      console.log(data);
      this.publications = data;
      for (let publication of this.publications) {
        this.getProcessesByPublicationId(publication);
      }
    }, error => {
      this.toastr.error('There was an error while getting your publications');
    });
  }

  private getProcessesByPublicationId(publication) {
    this.processService.getProcessByPublicationId(publication.id).subscribe(
      (data) => {
        publication.process = data;
        console.log(this.publications);
      },
      (error) => {
        this.toastr.error('Failed getting process.');
      }
    );
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

  checkRevision(publicationId: string) {
    let cond = false;
    for (let publication of this.publications) {
      if (publication.id === publicationId) {
        if (publication.process.processPhase === 'ZaReviziju') {
          cond = true;
          break;
        }
      }
    }
    return cond;
  }

  onPublicationRevision(publicationId: string) {
    if (!this.checkRevision(publicationId)) {
      this.toastr.error('Publication is not for revision yet.');
      return;
    }
    let dialogRef = this.dialog.open(PublicationDialogComponent, {data: {}});
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.publicationsService.addRevision(publicationId, result).subscribe(
          (data) => {
            this.getPublications();
          },
          (error) => {
            this.getPublications();
            this.toastr.error('There was an error while adding your revision');
          }
        );
      }
    });
  }
}
