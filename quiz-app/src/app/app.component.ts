import { Component } from '@angular/core';
import { Router, RouterOutlet, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn: boolean = false;
  userRole: string | null = '';
  currentRoute: string = '';
  dropdownVisible: boolean = false;

  constructor(private router: Router) {
    this.checkLoginStatus();

    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: any) => {
        this.currentRoute = event.urlAfterRedirects;
        this.checkLoginStatus();
        this.dropdownVisible = false; 
      });
  }

  checkLoginStatus() {
    if (typeof localStorage !== 'undefined') {
      this.userRole = localStorage.getItem('userRole');
      this.isLoggedIn = true;
    }
  }

  isAuthPage(): boolean {
    return ['/login', '/register', '/forgot-password'].includes(this.currentRoute);
  }

  logout() {
    localStorage.removeItem('userRole');
    localStorage.removeItem('user');
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }

  toggleDropdown() {
    this.dropdownVisible = !this.dropdownVisible;
  }

  navigateHome() {
    this.router.navigate(['/homepage']); 
  }

  changePassword() {
    this.router.navigate(['/change-password']); 
  }
}
