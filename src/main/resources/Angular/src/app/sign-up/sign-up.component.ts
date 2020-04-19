import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient, private snackBar: MatSnackBar) { }

  name = ''
  mobile = ''
  address = ''
  eid = null;
  uname = '';
  password = '';
  ngOnInit() {
  }

  submit() {
    let obj = {
      name: this.name,
      mobile: this.mobile,
      address: this.address,
      user_auth: {
        username: this.uname,
        password: this.password
      }
    }
    console.log(obj)
    this.http.post(environment.url + '/users', obj).subscribe(res => {
      console.log(res);
      this.router.navigateByUrl('/login')
      this.snackBar.open("User " + obj.name + " has been created", 'Dismiss');
    })
  }
}
