import { ToastrService } from 'ngx-toastr';
import { BusinessProcessService } from './../../services/business-process.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-business-process',
  templateUrl: './business-process.component.html',
  styleUrls: ['./business-process.component.css']
})
export class BusinessProcessComponent implements OnInit {

  processes: any[] = [];

  constructor(private businessProcessService: BusinessProcessService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
    this.getAllProcesses();
  }

  private getAllProcesses(): void {
    this.businessProcessService.getAllProcesses().subscribe(data => {
      this.processes = data;
    }, error => {
      this.toastr.error('There was an error while getting business processes data.');
    });
  }

  onClickSeeMore(processId: string): void {
    // TODO: Idi na komponentu za rad sa jednim procesom
  }
}
