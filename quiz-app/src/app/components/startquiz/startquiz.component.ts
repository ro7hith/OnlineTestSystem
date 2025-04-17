import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionRequestService } from '../../services/question-request.service';
import { QuestionRequest } from '../../classes/question-request';
import { QuestionsDataService } from '../../services/questions-data.service';

@Component({
  selector: 'app-startquiz',
  templateUrl: './startquiz.component.html',
  styleUrl: './startquiz.component.css'
})
export class StartquizComponent implements OnInit {
  questionRequest = {} as QuestionRequest;
  questions: any[] = [];

  totalTimeInSeconds: number = 0;
  interval: any;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private apiService: QuestionRequestService,
    private questionDataService: QuestionsDataService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.questionRequest.technology = params['technology'];
      this.questionRequest.noOfQuestions = params['numQuestions'];
      this.questionRequest.questionLevel = params['difficulty'];

      this.totalTimeInSeconds = Math.max(this.questionRequest.noOfQuestions * 60, 0);

      console.log("Start with following parameters");
      console.log("Technology:", this.questionRequest.technology);
      console.log("No of Questions:", this.questionRequest.noOfQuestions);
      console.log("Level:", this.questionRequest.questionLevel);
    });
  }

  startQuiz(): void {
    console.log('Starting quiz for:', this.questionRequest);

    this.apiService.sendQuizData(this.questionRequest).subscribe(
      response => {
        console.log('Questions fetched:', response);
        this.questions = response;

        
        this.questionDataService.setQuestions(this.questions);
        this.questionDataService.setTechnology(this.questionRequest.technology);
        this.questionDataService.setDifficulty(this.questionRequest.questionLevel);

        this.router.navigate(['/questions']);
      },
      error => {
        console.error('Error fetching quiz questions:', error);
      }
    );
  }

  ngOnDestroy(): void {
    clearInterval(this.interval);
  }
}
