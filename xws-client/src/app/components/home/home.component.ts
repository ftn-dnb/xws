import { ToastrService } from 'ngx-toastr';
import { PublicationsService } from './../../services/publications.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  publications: any[] = [];

  constructor(private publicationsService: PublicationsService,
              private toastr: ToastrService) {
  }

  ngOnInit() {
  }

  onSearch(): void {
    this.publicationsService.searchPublications().subscribe(data => {
      console.log(data);
      this.publications = data;
    }, error => {
      this.toastr.error('There was an error while getting publications data.');
    });
  }
}
