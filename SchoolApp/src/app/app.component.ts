import { Component } from '@angular/core';
import { StudentService } from './services/student.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'SchoolApp';
  isUserLoggedIn: boolean;

  constructor(private service: StudentService) {

  }

  ngOnInit() {
    this.isUserLoggedIn = this.service.isUserLoggedIn();
  }

}
