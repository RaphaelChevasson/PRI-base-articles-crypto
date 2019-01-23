import { Component } from '@angular/core';
import { HttpRequestService } from './services/http-request.service';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {



  public searchText = '';

  public searchBy = 'title';

  public postList: any = [];


  constructor(private httpClient: HttpClient) {

    this.httpClient.get('/api/posts').subscribe((response) => {
      console.log(response);
      this.postList = response;
    });

  }


  public search(form: NgForm) {
    console.log('search');
    console.log(form.form.value);
    let tag;

    switch (this.searchBy) {
      case 'title':
        tag = 'title';
        break;
      case 'keywords':
        tag = 'keywords';
        break;
      case 'author':
        tag = 'author';
        break;
      default: break;
    }

    this.httpClient.get('/api/posts/search?tag=' + tag + '&value=' + form.form.value['searchText'])
      .subscribe((response) => {
        console.log(response);
        this.postList = response;
      });


  }


}
