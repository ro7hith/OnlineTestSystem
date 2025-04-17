import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AssessmentDetailsDto } from '../../classes/assessment-details-dto';
import { AssessmentDetailsService } from '../../services/assessment-details.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent implements OnInit {
  assessmentId!: number;
  assessmentDetails: AssessmentDetailsDto[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private assessmentDetailsService: AssessmentDetailsService
  ) {}

  ngOnInit(): void {
    this.assessmentId = Number(this.route.snapshot.paramMap.get('assessmentId'));
    this.fetchAssessmentDetails();
  }

  fetchAssessmentDetails() {
    this.assessmentDetailsService.getAssessmentDetails(this.assessmentId)
      .subscribe(
        (data) => {
          this.assessmentDetails = data;
        },
        (error) => {
          console.error('Error fetching assessment details:', error);
        }
      );
  }
  getOptionClass(detail: AssessmentDetailsDto, option: string): string {
    if (detail.selectedOption === option) {
      return option === detail.correctAnswer ? 'correct' : 'incorrect';
    }
    return '';
  }
  getCorrectAnswerLabel(detail: AssessmentDetailsDto): string {
    if (detail.correctAnswer === detail.optionA) return 'A';
    if (detail.correctAnswer === detail.optionB) return 'B';
    if (detail.correctAnswer === detail.optionC) return 'C';
    if (detail.correctAnswer === detail.optionD) return 'D';
    return '';
  }
  retakeQuiz() {
    this.router.navigate(['/setup']);
  }
}
