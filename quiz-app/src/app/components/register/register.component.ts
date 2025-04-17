import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoggedInService } from '../../services/logged-in.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
})
export class RegisterComponent {
  user = {
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
    usertype: 'user',
    securityQuestion1: '',
    answer1: '',
    securityQuestion2: '',
    answer2: ''
  };

  questions = [
    "What is your mother's name?",
    "What is your birth city?",
    "What is your favorite teacher's name?",
    "What is your first school name?",
    "What is your childhood nickname?"
  ];

  constructor(private service: LoggedInService, private router: Router) {
    console.log("Security questions loaded:", this.questions);
  }

  registerUser(event: Event) {
    event.preventDefault();

    // Validation checks
    if (
      !this.user.name ||
      !this.user.email ||
      !this.user.password ||
      !this.user.confirmPassword ||
      !this.user.securityQuestion1 ||
      !this.user.answer1 ||
      !this.user.securityQuestion2 ||
      !this.user.answer2
    ) {
      alert("All fields including security questions are required!");
      return;
    }

    if (this.user.password !== this.user.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    const payload = { ...this.user };

    this.service.register(payload).subscribe(
      (res: any) => {
        alert(res.message || "Registered successfully.");
        this.router.navigate(['/login']);
      },
      err => {
        alert(err.error?.message || "Registration failed.");
        console.error("Registration Error:", err);
      }
    );
  }
}
