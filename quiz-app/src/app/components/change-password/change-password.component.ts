import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  oldPassword = '';
  newPassword = '';
  confirmPassword = '';

  constructor(private http: HttpClient, private router: Router) {}

  changePassword() {
    if (this.newPassword !== this.confirmPassword) {
      alert("New passwords don't match.");
      return;
    }

    const token = localStorage.getItem('token');
    if (!token) {
      alert("Unauthorized: No token found.");
      return;
    }

    let decoded: { sub: string };
    try {
      decoded = jwtDecode(token);
    } catch (e) {
      alert("Invalid token.");
      return;
    }

    const email = decoded.sub;

    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });

    this.http.post(
      'http://localhost:8081/auth/change-password',
      {
        email,
        oldPassword: this.oldPassword,
        newPassword: this.newPassword
      },
      { headers }
    ).subscribe({
      next: () => {
        alert('Password changed successfully.');
        this.router.navigateByUrl('/login');
      },
      error: (err) => {
        alert('Password change failed: ' + (err.error?.error || err.statusText));
      }
    });
  }
}
