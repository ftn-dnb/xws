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

  @ViewChild('filterRec', {static: false})
  filterRec: ElementRef;

  @ViewChild('filterNaslov', {static: false})
  filterNaslov: ElementRef;

  @ViewChild('filterJezik', {static: false})
  filterJezik: ElementRef;

  @ViewChild('filterVerzija', {static: false})
  filterVerzija: ElementRef;

  @ViewChild('filterTip', {static: false})
  filterTip: ElementRef;

  @ViewChild('filterAutor', {static: false})
  filterAutor: ElementRef;

  @ViewChild('filterDatumPStart', {static: false})
  filterDatumPStart: ElementRef;

  @ViewChild('filterDatumPEnd', {static: false})
  filterDatumPEnd: ElementRef;

  @ViewChild('filterDatumSStart', {static: false})
  filterDatumSStart: ElementRef;

  @ViewChild('filterDatumSEnd', {static: false})
  filterDatumSEnd: ElementRef;

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

  onSearchAdvanced(): void {
    this.fixRequest();
    
    const searchObject = {
      keyword: this.filterRec.nativeElement.value,
      title: this.filterNaslov.nativeElement.value,
      language: this.filterJezik.nativeElement.value,
      type: this.filterTip.nativeElement.value,
      autor1: this.filterAutor.nativeElement.value,
      autor2: '',
      startDateCreated: this.filterDatumSStart.nativeElement.value,
      endDateCreated:  this.filterDatumSEnd.nativeElement.value,
      startDatePublished: this.filterDatumPStart.nativeElement.value,
      endDatePublished: this.filterDatumPEnd.nativeElement.value
    };
    this.publicationsService.searchPublicationsMetadata(searchObject).subscribe(data => {
      this.publications = data;
    }, error => {
      this.toastr.error('There was an error while getting publications data.');
    });
  }

  onSeeDetails(id: string): void {
    this.router.navigate([PUBLICATION_PATH_NO_PARAM, id]);
  }

  private fixRequest() {
    if (this.filterRec.nativeElement.value === undefined) {
      this.filterRec.nativeElement.value = '';
    }

    if (this.filterJezik.nativeElement.value === undefined) {
      this.filterJezik.nativeElement.value = '';
    }

    if (this.filterTip.nativeElement.value === undefined) {
      this.filterTip.nativeElement.value = '';
    }

    if (this.filterAutor.nativeElement.value === undefined) {
      this.filterAutor.nativeElement.value = '';
    }

    if (this.filterDatumPStart.nativeElement.value === undefined) {
      this.filterDatumPStart.nativeElement.value = '';
    }

    if (this.filterDatumPEnd.nativeElement.value === undefined) {
      this.filterDatumPEnd.nativeElement.value = '';
    }

    if (this.filterDatumSStart.nativeElement.value === undefined) {
      this.filterDatumSStart.nativeElement.value = '';
    }

    if (this.filterDatumSEnd.nativeElement.value === undefined) {
      this.filterDatumSEnd.nativeElement.value = '';
    }
  }
}
