import { CommonModule, Location } from '@angular/common';
import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ScoresDto } from '../../classes/scores-dto';
import { AssessmentRequest } from '../../classes/assessment-request';
import { QuestionsDataService } from '../../services/questions-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-questions',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './questions.component.html',
  styleUrl: './questions.component.css'
})
export class QuestionsComponent implements OnInit, AfterViewInit, OnDestroy {
  questions: any[] = [];
  currentQuestionIndex: number = 0;
  selectedAnswers: { [key: number]: string } = {};
  responses: ScoresDto[] = [];

  timerMinutes: number = 0;
  timerSeconds: number = 0;
  totalTimeInSeconds: number = 0;
  timerInterval: any;

  isAutoSubmit: boolean = false;

  private apiUrl = 'http://localhost:8081/api/assess/start';

  constructor(
    private questionDataService: QuestionsDataService,
    private http: HttpClient,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.questions = this.questionDataService.getQuestions();
    this.totalTimeInSeconds = Math.max(this.questions.length * 60, 0);
    this.startTimer();
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.currentQuestionIndex = 0;
    }, 0);
  }

  ngOnDestroy(): void {
    clearInterval(this.timerInterval);
  }

  startTimer(): void {
    this.updateTimerDisplay();

    this.timerInterval = setInterval(() => {
      if (this.totalTimeInSeconds > 0) {
        this.totalTimeInSeconds--;
        this.updateTimerDisplay();
      } else {
        clearInterval(this.timerInterval);
        this.isAutoSubmit = true;
        alert('⏰ Time is up! Auto-submitting your quiz.');
        this.onSubmit();
      }
    }, 1000);
  }

  updateTimerDisplay(): void {
    this.timerMinutes = Math.floor(this.totalTimeInSeconds / 60);
    this.timerSeconds = this.totalTimeInSeconds % 60;
  }

  onNext() {
    const currentQuestionId = this.questions[this.currentQuestionIndex].questionId;
    const selectedAnswer = this.selectedAnswers[currentQuestionId];

    if (!selectedAnswer) {
      alert('Please select an option before proceeding to the next question.');
      return;
    }

    if (this.currentQuestionIndex < this.questions.length - 1) {
      this.currentQuestionIndex++;
    }
  }

  get isLastQuestion() {
    return this.currentQuestionIndex === this.questions.length - 1;
  }

  setAnswer(questionId: number, option: string): void {
    const question = this.questions.find(q => q.questionId === questionId);
    let selectedAnswerValue: string = '';

    if (question) {
      switch (option) {
        case 'A': selectedAnswerValue = question.optionA; break;
        case 'B': selectedAnswerValue = question.optionB; break;
        case 'C': selectedAnswerValue = question.optionC; break;
        case 'D': selectedAnswerValue = question.optionD; break;
      }
    }
    this.selectedAnswers[questionId] = selectedAnswerValue;
  }

  collectAnswers(): void {
    this.responses = this.questions.map((question) => {
      const selectedAnswer = this.selectedAnswers[question.questionId] || '';
      const correctAnswer = question.correctAnswer || '';

      return new ScoresDto(
        question.questionId,
        selectedAnswer,
        correctAnswer
      );
    });
  }

  onSubmit() {
    const token = localStorage.getItem('token');
    const username = localStorage.getItem('username');

    if (!token || !username) {
      alert('Session expired or quiz setup missing. Please login and try again.');
      this.router.navigate(['/login']);
      return;
    }

    
    if (!this.isAutoSubmit) {
      const unanswered = this.questions.some(q => !this.selectedAnswers[q.questionId]);
      if (unanswered) {
        alert('Please answer all questions before submitting.');
        return;
      }
    }

    this.collectAnswers();

    const today = new Date();
    const formattedDate = today.toISOString().split('T')[0];

    const assessment: AssessmentRequest = new AssessmentRequest(
      username,
      this.questionDataService.getTechnology(),
      formattedDate,
      this.questionDataService.getDifficulty(),
      this.responses.length,
      this.responses
    );

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    this.http.post<any>(this.apiUrl, assessment, { headers }).subscribe(
      response => {
        console.log("Assessment submitted successfully", response);
        const assessmentId = response.assessmentId;

        this.router.navigateByUrl('/', { replaceUrl: true }).then(() => {
          this.router.navigate(['/scorecard', assessmentId], { replaceUrl: true });
        });
      },
      error => {
        console.error("Error submitting assessment", error);
      }
    );
  }
}
