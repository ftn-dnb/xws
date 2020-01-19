import { ToastrService } from 'ngx-toastr';
import { PublicationsService } from './../../services/publications.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-publications',
  templateUrl: './my-publications.component.html',
  styleUrls: ['./my-publications.component.css']
})
export class MyPublicationsComponent implements OnInit {

  publications: any[] = [];

  constructor(private publicationsService: PublicationsService,
              private toastr: ToastrService) { 
  }

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

  onDeletePublication(publicationId: number): void {
    console.log("Delete pub with id " + publicationId);
  }
}
