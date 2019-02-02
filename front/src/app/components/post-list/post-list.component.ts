import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  public size = 10;
  public currentPage = 1;
  public totalPages = 0;
  public totalElements = 0;
  public pageSize = 10;

  public searchText = '';

  public searchBy = 'title';

  public postList: any = [];

  @ViewChild('searchForm')
  private searchForm: NgForm;


  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.httpClient.get(`/api/posts?page=${this.currentPage - 1}&size=${this.size}`).subscribe((response: any) => {
      console.log(response.content);
      this.postList = response.content;
      this.totalElements = response.totalElements;
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

    this.httpClient
      .get(`/api/posts/search?tag=${tag}&value=${form.form.value['searchText']}&page=${this.currentPage - 1}&size=${this.size}`)
      .subscribe((response: any) => {
        console.log(response);
        this.postList = response.content;
        this.totalElements = response.totalElements;
      });

  }

  pageChange(event) {
    this.search(this.searchForm);
  }

}
