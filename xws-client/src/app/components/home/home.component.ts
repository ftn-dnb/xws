import { PUBLICATION_PATH_NO_PARAM } from './../../config/router-paths';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PublicationsService } from './../../services/publications.service';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  publications: any[] = [];

  @ViewChild('filterInput', {static: false})
  filterInputElement: ElementRef;

  constructor(private publicationsService: PublicationsService,
              private toastr: ToastrService,
              private router: Router) {
  }

  ngOnInit() {
    this.publicationsService.getPublications().subscribe(
      data => this.publications = data,
      error => {
        this.toastr.error('There was an error while getting publications.');
      }
    );
  }


  onSearch(): void {
    const searchObject = {
      filterText: this.filterInputElement.nativeElement.value
    };
    this.publicationsService.searchPublications(searchObject).subscribe(data => {
      console.log(data);
      this.publications = data;
      localStorage.setItem('filterParam', searchObject.filterText);
    }, error => {
      this.toastr.error('There was an error while getting publications data.');
    });
  }

  onSeeDetails(id: string): void {
    this.router.navigate([PUBLICATION_PATH_NO_PARAM, id]);
  }
}
