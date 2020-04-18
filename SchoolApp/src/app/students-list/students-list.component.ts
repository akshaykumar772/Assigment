import { Component, OnInit, ViewChild } from '@angular/core';
import { StudentService } from '../services/student.service';
import { AgGridAngular } from 'ag-grid-angular';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Student } from '../entity/Student';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {

  @ViewChild('agGrid') agGrid: AgGridAngular;
  rowData: Student[] = [];
  rollno: number;
  columnDefs = [
    {headerName: 'Select', checkboxSelection: true },
    {headerName: 'Rollno', field: 'rollno'},
    {headerName: 'Name', field: 'name', sortable: true},
    {headerName: 'Username', field: 'username'},
    {headerName: 'Gender', field: 'gender'},
    {headerName: 'Address', field: 'address'}
];
 
  constructor(
    private service: StudentService,
    private route: Router,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getStudents();
  }

  editStudentDetails() {
    var data = this.getSelectedRow();
    this.route.navigate(['/studentDetails', data.rollno]);
  }

  deleteStudent() {
    var data = this.getSelectedRow();
    this.service.deleteStudent(data.id).subscribe(student => {
      let snackBarRef = this.snackBar.open("Student Deleted", "Close", {
        duration: 2000,
      });

      snackBarRef.onAction().subscribe(() => {
        snackBarRef.dismiss();
      });
      this.getStudents();    
    })
  }

  getStudents() {
    this.service.getStudentsList().subscribe(students => {
      this.rowData = students;
    })
  };

  getSelectedRow() {
    const selectedNode = this.agGrid.api.getSelectedNodes();
    return selectedNode[0].data;
  };
}
