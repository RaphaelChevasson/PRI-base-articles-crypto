import { Component, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  public size = 10;
  public currentPage = 0;
  public totalPages = 0;

  public searchText = '';

  public searchBy = 'title';

  public postList: any = [];

  @ViewChild('searchForm')
  private searchForm: NgForm;


  constructor(private httpClient: HttpClient) {

    this.httpClient.get(`/api/posts?page=${this.currentPage}&size=${this.size}`).subscribe((response: any) => {
      console.log(response.content);
      this.postList = response.content;
      this.totalPages = response.totalPages;
      this.currentPage = response.number;
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

    this.httpClient.get(`/api/posts/search?tag=${tag}&value=${form.form.value['searchText']}&page=${this.currentPage}&size=${this.size}`)
      .subscribe((response: any) => {
        console.log(response);
        this.postList = response.content;
        this.totalPages = response.totalPages;
        this.currentPage = response.number;
      });

  }

  nextPage() {
    this.currentPage++;
    this.search(this.searchForm);
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.search(this.searchForm);

    }
  }


}
