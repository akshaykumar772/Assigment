import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

import { StudentService } from '../services/student.service';
import { Student } from '../entity/Student';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  rollnumber: any;
  studentDetails: Student;

  constructor(
    private route: ActivatedRoute,
    private service: StudentService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.rollnumber = this.route.snapshot.paramMap.get("rollno");
    this.service.getStudentDetails(this.rollnumber).subscribe(student => {
      this.studentDetails = student;
      console.log(this.studentDetails);
    })
  }

  saveStudentDetails() {
    this.service.updateStudentDetails(this.studentDetails).subscribe(student => {
      let snackBarRef = this.snackBar.open("Student Details Updated", "Close", {
        duration: 2000,
      });

      snackBarRef.onAction().subscribe(() => {
        snackBarRef.dismiss();
      });
    });
    console.log(this.studentDetails);
  }

}
