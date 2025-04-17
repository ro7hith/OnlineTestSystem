export class UserTopicAverage {
    email: string;
  technology: string;
  questionLevel: string;
  averageScore: number;

  constructor(email:string, technology:string, questionLevel:string, averageScore:number){
    this.email = email;
    this.technology = technology;
    this.questionLevel= questionLevel;
    this.averageScore = averageScore;
  }
}
