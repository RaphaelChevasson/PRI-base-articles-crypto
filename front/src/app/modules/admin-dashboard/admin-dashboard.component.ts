import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { DataSourceInfoModel } from 'src/app/models/hal-info.model';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  public halInfo: DataSourceInfoModel;
  public eprintInfo: DataSourceInfoModel;

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.httpClient.get('/api/hal/info').subscribe(
      (response: DataSourceInfoModel) => {
        this.halInfo = response;
        console.log(response);

      }, (error) => {
        // handle error
        console.error(error);
      });

    this.httpClient.get('/api/eprint/info').subscribe(
      (response: DataSourceInfoModel) => {
        this.eprintInfo = response;
        console.log(response);

      }, (error) => {
        // handle error
        console.error(error);
      });
  }

}
