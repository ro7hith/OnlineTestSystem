import { Component, OnInit } from '@angular/core';
import { QuizserviceService } from '../../services/quizservice.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-score-component',
  templateUrl: './score-component.component.html',
  styleUrls: ['./score-component.component.css'],
  providers: [QuizserviceService]
})
export class ScoreComponentComponent implements OnInit {
  userScore: string = '';
  scoreStats: string = '';
  assessmentId!: number;

  constructor(
    private quizService: QuizserviceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const idParam = this.route.snapshot.paramMap.get('assessmentId');
    if (idParam) {
      this.assessmentId = Number(idParam);
      console.log('Assessment ID:', this.assessmentId);
      this.loadScores();
    } else {
      console.error('No valid assessmentId found!');
      this.userScore = 'Invalid assessment ID.';
      this.scoreStats = 'Cannot fetch stats without valid assessment.';
    }
  }

  loadScores() {
    this.quizService.getScores(this.assessmentId).subscribe(
      (response: any) => {
        this.userScore = response.score; 
        console.log('User Score:', this.userScore);
      },
      (error) => {
        console.error('Error fetching score:', error);
        this.userScore = 'Error fetching score.';
      }
    );

    this.quizService.getStats(this.assessmentId).subscribe(
      (response: any) => {
        this.scoreStats = response.statistics; 
        console.log('Score Stats:', this.scoreStats);
      },
      (error) => {
        console.error('Error fetching stats:', error);
        this.scoreStats = 'Error fetching stats.';
      }
    );
  }

  retakeQuiz() {
    this.router.navigate(['/setup']);
  }

  showResults() {
    this.router.navigate(['/details', this.assessmentId]);
  }
}
