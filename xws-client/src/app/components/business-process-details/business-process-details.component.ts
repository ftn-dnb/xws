import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BusinessProcessService } from './../../services/business-process.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-business-process-details',
  templateUrl: './business-process-details.component.html',
  styleUrls: ['./business-process-details.component.css']
})
export class BusinessProcessDetailsComponent implements OnInit {

  private processId: string = '';
  process = {};

  constructor(private businessProcessService: BusinessProcessService, 
              private toastr: ToastrService, 
              private activatedRoute: ActivatedRoute) { 
    this.processId = this.activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.getProcessData();
  }

  private getProcessData(): void {
    this.businessProcessService.getProcess(this.processId).subscribe(data => {
      console.log(data);
    }, error => {
      this.toastr.error('There was an error while getting the data for business process');
    });
  }
}
