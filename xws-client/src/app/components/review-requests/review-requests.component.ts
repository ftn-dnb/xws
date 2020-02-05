import { ToastrService } from 'ngx-toastr';
import { BusinessProcessService } from './../../services/business-process.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ReviewDialogComponent } from './dialog/review-dialog/review-dialog.component';

@Component({
  selector: 'app-review-requests',
  templateUrl: './review-requests.component.html',
  styleUrls: ['./review-requests.component.css']
})
export class ReviewRequestsComponent implements OnInit {

  requests: any[] = [];

  constructor(private businessProcessService: BusinessProcessService,
              private toastr: ToastrService,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.getRequests();
  }

  private getRequests(): void {
    this.businessProcessService.getMyReviewRequests().subscribe(data => {
      this.requests = data;
    }, error => {
      this.toastr.error('There was an error while getting the data about review requests');
    });
  }

  onClickAccept(processId: string): void {
    this.businessProcessService.acceptReviewRequest(processId).subscribe(data => {
      this.getRequests();
      this.toastr.success('You accepted review request');
    }, error => {
      this.toastr.error('Error while accepting review request');
    });
  }

  onClickDecline(processId: string): void {
    this.businessProcessService.declineReviewRequest(processId).subscribe(data => {
      this.getRequests();
      this.toastr.success('You declined review request');
    }, error => {
      this.toastr.error('Error while declining review request');
    });
  }

  checkRevision(processId: string) {
    let cond = true;
    for (let request of this.requests) {
      if (request.processId === processId) {
        if (request.publicationPhase === 'ZA_REVIZIJU') {
          cond = false;
          break;
        }
      }
    }
    return cond;
  }

  onSubmitReview(processId: string): void {
    if (!this.checkRevision(processId)) {
      this.toastr.error('Process is not under review.');
      return;
    }
    const dialogRef = this.dialog.open(ReviewDialogComponent, {data: {}});
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.businessProcessService.addReview(processId, result).subscribe(
          (data) => {
            this.toastr.success('Review successfuly added.');
          },
          (error) => {
            this.toastr.error(error);
          }
        );
      }
    });
  }
}
