import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-quiz-setup',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './quiz-setup.component.html',
  styleUrl: './quiz-setup.component.css'
})
export class QuizSetupComponent implements OnInit {
  username: string | null = '';
  technologies: string[] = ['Python', 'Java', 'Angular','React','Go','AutoCAD','SAP Signavio','SAP HANA'];
  difficultLevel: string[] = ['Easy', 'Medium', 'Hard'];
  
  selectedTechnology: string = '';
  selectedDifficulty: string = '';
  selectedNumberOfQuestions: number | null = null;

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('username');

    this.route.queryParams.subscribe(params => {
      this.selectedTechnology = params['technology'] || '';
      this.selectedDifficulty = params['difficulty'] || '';
      this.selectedNumberOfQuestions = +params['numQuestions'] || 5;
    });
    
  }

  onSubmit() {
    console.log('Selected Technology:', this.selectedTechnology);
    console.log('Selected Difficulty:', this.selectedDifficulty);
    console.log('Selected Number of Questions:', this.selectedNumberOfQuestions);
    
    
    if (this.selectedNumberOfQuestions === null) {
      return;
    }

    this.router.navigate(['/rules'], {
      queryParams: {
        technology: this.selectedTechnology,
        difficulty: this.selectedDifficulty,
        numQuestions: this.selectedNumberOfQuestions
      }
    });
  }
}
