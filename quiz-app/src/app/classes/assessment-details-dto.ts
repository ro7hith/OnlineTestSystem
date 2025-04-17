export class AssessmentDetailsDto {
    assessmentId: number;
    question: string;
    optionA: string;
    optionB: string;
    optionC: string;
    optionD: string;
    selectedOption: string;
    correctAnswer: string;
  
    constructor(
      assessmentId: number,
      question: string,
      optionA: string,
      optionB: string,
      optionC: string,
      optionD: string,
      selectedOption: string,
      correctAnswer: string
    ) {
      this.assessmentId = assessmentId;
      this.question = question;
      this.optionA = optionA;
      this.optionB = optionB;
      this.optionC = optionC;
      this.optionD = optionD;
      this.selectedOption = selectedOption;
      this.correctAnswer = correctAnswer;
    }
  }
  