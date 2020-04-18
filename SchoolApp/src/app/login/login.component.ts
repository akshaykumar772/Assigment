import { Component, OnInit } from '@angular/core';
import { User } from '../entity/User';
import { StudentService } from '../services/student.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User;
  constructor(private service: StudentService,
    private route: Router) { }

  ngOnInit() {
  }

  authenticate() {
    this.service.authenticate(this.user).subscribe(userData => {
      console.log(userData);
      this.route.navigateByUrl('/studentsList');
    })
  }

}
