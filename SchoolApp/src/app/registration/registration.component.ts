import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { MatSnackBar } from '@angular/material/snack-bar';

import { StudentService } from '../services/student.service';
import { Student } from '../entity/Student';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  studentDetails: Student = new Student;
  errorMsg: string = "";

  constructor(
    private service: StudentService,
    private route: Router,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  registerStudent() {
    console.log(this.studentDetails);
    this.service.addStudent(this.studentDetails).subscribe(student => {
      if(student.errorMsg) {
        let snackBarRef = this.snackBar.open(student.errorMsg, "Close", {
          duration: 2000,
        });

        snackBarRef.onAction().subscribe(() => {
          snackBarRef.dismiss();
        });
      } else {
        let snackBarRef = this.snackBar.open("Registration Successful", "Close", {
          duration: 2000,
        });

        snackBarRef.onAction().subscribe(() => {
          snackBarRef.dismiss();
        });
        this.route.navigateByUrl('/login');
      }
    })
    
    

  }
}
