import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BusinessProcessService } from './../../services/business-process.service';
import { Component, OnInit } from '@angular/core';
import { elementEventFullName } from '@angular/compiler/src/view_compiler/view_compiler';

@Component({
  selector: 'app-business-process-details',
  templateUrl: './business-process-details.component.html',
  styleUrls: ['./business-process-details.component.css']
})
export class BusinessProcessDetailsComponent implements OnInit {

  private processId: string = '';
  process: any = {};
  possibleReviewers: any[] = [];
  choosenReviewers: any[] = [];
  reviews: any[] = [];

  selectedPhase = 'ZaReviziju';

  constructor(private businessProcessService: BusinessProcessService,
              private toastr: ToastrService,
              private activatedRoute: ActivatedRoute) {
    this.processId = this.activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.getProcessData();
    this.getPossibleReviewers();
  }

  private getProcessData(): void {
    this.businessProcessService.getProcess(this.processId).subscribe(data => {
      console.log(data);
      this.process = data;
      this.getReviews(this.process.id);
    }, error => {
      this.toastr.error('There was an error while getting the data for business process');
    });
  }

  private getPossibleReviewers(): void {
    this.businessProcessService.recommendReviewers(this.processId).subscribe(data => {
      this.possibleReviewers = data.reverse();
    }, error => {
      this.toastr.error('There was an error while making reviewer recommendation.');
    });
  }

  private getReviews(processId) {
    this.businessProcessService.getReviewsByProcessId(processId).subscribe(
      (data) => {
        this.reviews = data;
        console.log(this.reviews);
      },
      (error) => {
        this.toastr.error('There was an error while retreiving reviews.');
      }
    );
  }

  addReviewerToList(id: string): void {
    const elementIndex = this.possibleReviewers.findIndex(element => element.id === id);
    const reviewer = this.possibleReviewers[elementIndex];
    this.possibleReviewers.splice(elementIndex, 1);
    this.choosenReviewers.push(reviewer);
  }

  removeReviewerFromList(id: string): void {
    const elementIndex = this.choosenReviewers.findIndex(element => element.id === id);
    const reviewer = this.choosenReviewers[elementIndex];
    this.choosenReviewers.splice(elementIndex, 1);
    this.possibleReviewers.push(reviewer);
  }

  addReviewersToProcess(): void {
    if (this.choosenReviewers.length === 0) {
      this.toastr.warning('Please choose at least 1 reviewer.');
      return;
    }

    const userIds: string[] = this.choosenReviewers.map(reviewer => reviewer.id);

    this.businessProcessService.addReviewers(this.processId, userIds).subscribe(data => {
      this.toastr.success('Reviewers have been successfully added.');
    }, error => {
      this.toastr.error('Error while adding reviewers to the business process.');
    });
  }

  changePhase() {
    this.businessProcessService.changePhase(this.process.id, this.selectedPhase).subscribe(
      (data) => {
        this.toastr.success('Successfuly changed.');
      },
      (error) => {
        this.toastr.error('Failure while changing.');
      }
    );
  }
}
