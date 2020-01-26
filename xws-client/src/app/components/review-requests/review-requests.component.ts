import { ToastrService } from 'ngx-toastr';
import { BusinessProcessService } from './../../services/business-process.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-review-requests',
  templateUrl: './review-requests.component.html',
  styleUrls: ['./review-requests.component.css']
})
export class ReviewRequestsComponent implements OnInit {

  requests: any[] = [];

  constructor(private businessProcessService: BusinessProcessService,
              private toastr: ToastrService) { 
  }

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
}
