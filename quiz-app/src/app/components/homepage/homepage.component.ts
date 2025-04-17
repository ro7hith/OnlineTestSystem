import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StatsService } from '../../services/stats.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-homepage',
  imports: [CommonModule],
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  username: string | null = '';
  userEmail: string | null = '';

  statsTable: any = {};
  technologies = ['Java', 'Python', 'Angular','Go','React','AutoCAD','SAP Signavio','SAP HANA'];
  levels = ['Easy', 'Medium', 'Hard'];
recommendedTopic: any;

  constructor(private router: Router, private statsService: StatsService) {}

  ngOnInit(): void {
    const decoded = this.getDecodedToken();
    const fullEmail = decoded?.sub || '';
    this.userEmail = fullEmail.split('@')[0]; 
    this.username = this.userEmail;
  
    if (this.userEmail) {
      this.statsService.getUserStats(this.userEmail).subscribe((data) => {
        this.prepareStatsTable(data);
      });
    }
  }
  
  prepareStatsTable(data: any[]) {
    this.technologies.forEach(tech => {
      this.statsTable[tech] = { Easy: 0, Medium: 0, Hard: 0 };
    });
    for (let item of data) {
      const tech = item.technology;
      const level = item.questionLevel;
      const score = item.averageScore;
  
      if (this.statsTable[tech]) {
        this.statsTable[tech][level] = score;
      }
    }
  
    let minScore = Infinity;
    let recommended = '';
  
    for (let tech of this.technologies) {
      for (let level of this.levels) {
        const score = this.statsTable[tech][level];
  
        if (score < minScore) {
          minScore = score;
          recommended = `${tech} - ${level}`;
        }
      }
    }
  
    this.recommendedTopic = recommended;
  }
  

  getDecodedToken(): any {
    const token = localStorage.getItem('token');
    if (!token) return null;
  
    try {
      const payload = token.split('.')[1];
      return JSON.parse(atob(payload));
    } catch (e) {
      console.error('Failed to decode token', e);
      return null;
    }
  }
  

  scrollToSection(sectionId: string) {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  }

  startRecommendedQuiz() {
    if (!this.recommendedTopic) {
      alert("No recommendation available yet.");
      return;
    }
  
    const [technology, difficulty] = this.recommendedTopic.split(" - ");
  
    this.router.navigate(['/setup'], {
      queryParams: {
        technology: technology,
        difficulty: difficulty,
        numQuestions: 5 
      }
    });
  }
  

  goToQuizSetup() {
    this.router.navigate(['/setup']);
  }
}
