import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoggedInService } from '../../services/logged-in.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  user = { email: '', password: '' };

  constructor(private service: LoggedInService, private router: Router) {}

  loginUser(event: Event) {
    event.preventDefault();

    if (!this.user.email || !this.user.password) {
      alert("Please enter email and password!");
      return;
    }

    this.service.validate(this.user).subscribe(
      (response: { token: string }) => {
        if (!response.token) {
          alert("Login failed: No token received!");
          console.error("No token in response:", response);
          return;
        }

        localStorage.setItem('token', response.token);

        const decodedToken: { usertype: string; sub: string; iat: number; exp: number } = jwtDecode(response.token);
        const role = decodedToken.usertype;
        const fullEmail = decodedToken.sub;
        const username = fullEmail.split('@')[0]; 

        localStorage.setItem('role', role);
        localStorage.setItem('username', username); 

        console.log("Login successful! Token stored:", response.token);
        console.log("Decoded role from token:", role);
        console.log("Extracted username:", username);

        if (role === 'admin') {
          this.router.navigate(['/admin']);
        } else if (role === 'user') {
          this.router.navigate(['/homepage']);
        } else {
          alert("Unknown role! Redirecting to login.");
          this.router.navigate(['/login']);
        }
      },
      (error) => {
        alert("Login Failed! Check your credentials.");
        console.error("Login error:", error);
      }
    );
  }
}
