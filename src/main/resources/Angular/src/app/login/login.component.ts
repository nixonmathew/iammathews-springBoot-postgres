import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient, private snackBar: MatSnackBar) { }

  userName = '';
  password = '';

  ngOnInit() {
  }

  submit() {
    let obj = {
      username: this.userName,
      password: this.password
    }
    this.http.post(environment.url + '/users/login', obj).subscribe(res => {
      console.log(res);
      this.router.navigateByUrl('/home');
      this.snackBar.open("Login Successful", 'Dismiss');
    })
  }
}
