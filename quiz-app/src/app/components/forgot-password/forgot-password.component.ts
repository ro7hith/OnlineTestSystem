import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  email = '';
  question1 = '';
  question2 = '';
  answer1 = '';
  answer2 = '';
  newPassword = '';
  step = 1;

  constructor(private http: HttpClient, private router: Router) {}

  fetchQuestions() {
    this.http.post<any>('http://localhost:8081/auth/forgot-password', { email: this.email })
      .subscribe(res => {
        this.question1 = res.securityQuestion1;
        this.question2 = res.securityQuestion2;
        this.step = 2;
      });
  }

  resetPassword() {
    this.http.post('http://localhost:8081/auth/reset-password', {
      email: this.email,
      answer1: this.answer1,
      answer2: this.answer2,
      password: this.newPassword
    }).subscribe(() => {
      alert('Password reset successfully');
      this.router.navigateByUrl('/login');
    });
  }

  navigateToLogin(): void {
    this.router.navigate(['/login']);
  }
}
