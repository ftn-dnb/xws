import { ToastrService } from 'ngx-toastr';
import { PublicationsService } from './../../services/publications.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-publication',
  templateUrl: './publication.component.html',
  styleUrls: ['./publication.component.css']
})
export class PublicationComponent implements OnInit {

  private id: string;
  publication: any = {};

  constructor(private route: ActivatedRoute,
              private toastr: ToastrService,
              private publicationsService: PublicationsService) { 
    this.id = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit() {
    this.getPublication();
  }

  private getPublication(): void {
    this.publicationsService.getPublication(this.id).subscribe(data => {
      this.publication = data;
    }, error => {
      this.toastr.error('There was an error while getting the publication data');
    });
  }
}
