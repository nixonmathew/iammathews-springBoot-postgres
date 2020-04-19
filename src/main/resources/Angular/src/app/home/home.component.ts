import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient) { }
  sortedCol = 'ascEID'
  users = [];
  ngOnInit() {
    this.getAllData();
  }
  getAllData() {
    let url = environment.url + '/users/';
    this.http.get(url).subscribe((res: any) => {
      console.log(res);
      this.users = res;
    })
  }
  sort(col) {
    let sortDir = this.getColSorted(col).substring(0, 3);
    let url = environment.url + "/users/sort/" + col + '/sortDir/' + sortDir
    this.http.get(url).subscribe((res: any) => {
      this.users = res;
    })
  }
  filter(value, col) {
    if (value.length) {
      console.log(value, col)
      let url = environment.url + "/users/filter/" + col + "/" + value
      this.http.get(url).subscribe((res: any) => {
        this.users = res;
      })
    }
    else {
      this.getAllData();
    }
  }
  getColSorted(col) {
    switch (col) {
      case 'eid':
        return this.sortedCol.charAt(0) === 'a' ? this.sortedCol = 'dscEID' : this.sortedCol = 'ascEID'
      case 'name':
        return this.sortedCol.charAt(0) === 'a' ? this.sortedCol = 'dscName' : this.sortedCol = 'ascName'
      case 'mobile':
        return this.sortedCol.charAt(0) === 'a' ? this.sortedCol = 'dscMobile' : this.sortedCol = 'ascMobile'
      case 'address':
        return this.sortedCol.charAt(0) === 'a' ? this.sortedCol = 'dscAddress' : this.sortedCol = 'ascAddress'
      default:
        alert("Wrong Grade.........");
    }

  }

}
