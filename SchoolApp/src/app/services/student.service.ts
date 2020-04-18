import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Student } from '../entity/Student';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../entity/User';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  url: string = 'http://localhost:8080/studentController';
  header = new HttpHeaders({
    'Content-Type': 'application/json'
  })

  constructor(private http: HttpClient) { }

  authenticate(user: User) {
    var url = this.url + '/authenticate';
    return this.http.post<any>(url, user, { headers: this.header }).pipe(
      map(userData => {
        sessionStorage.setItem("username", user.username);
        let tokenStr = "Bearer " + userData.token;
        sessionStorage.setItem("token", tokenStr);
        return userData;
      }))
  };

  isUserLoggedIn() {
    let user = sessionStorage.getItem("username");
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem("username");
  }

  getStudentsList(): Observable<Student[]> {
    var url = this.url + '/getStudentList';
   
    return this.http.get<Student[]>(url, { headers: this.header });
  }

  addStudent(studentDetails: Student): Observable<Student> {
    var url = this.url + '/addStudent';
    return this.http.post<Student>(url, studentDetails, { headers: this.header });
  }

  updateStudentDetails(studentDetails: Student): Observable<Student> {
    var url = this.url + '/updateStudentDetails';
    return this.http.post<Student>(url, studentDetails, { headers: this.header });
  }

  getStudentDetails(rollno: any): Observable<Student> {
    var url = this.url + '/getStudentDetails?rollno=' + rollno;
    return this.http.get<Student>(url, { headers: this.header });
  }

  deleteStudent(id: string): Observable<{}> {
    var url = this.url + '/deleteStudent?id=' + id;
    return this.http.delete(url, {headers: this.header});
  }
}
