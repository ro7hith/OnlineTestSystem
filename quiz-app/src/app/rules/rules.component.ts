import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { QuestionRequest } from '../classes/question-request';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-rules',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.css']
})
export class RulesComponent implements OnInit {
  agreed: boolean = false;
  questionRequest = {} as QuestionRequest;

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.questionRequest.technology = params['technology'];
      this.questionRequest.questionLevel = params['difficulty'];
      this.questionRequest.noOfQuestions = params['numQuestions'];

      console.log('Received in rules.component:', this.questionRequest);
    });
  }

  proceedToQuiz(): void {
    if (this.agreed) {
      this.router.navigate(['/beginQuiz'], {
        queryParams: {
          technology: this.questionRequest.technology,
          difficulty: this.questionRequest.questionLevel,
          numQuestions: this.questionRequest.noOfQuestions
        }
      });
    }
  }
}
