import { ScoresDto } from "./scores-dto";

ScoresDto

export class AssessmentRequest {

    email :string;
	technology :string;
	assessmentDate : string;
	questionLevel:string;
	noOfQuestions:number;
	scoresdto : ScoresDto[];

    constructor(email:string,technology:string,assessmentDate:string,questionLevel:string,noOfQuestions:number,scoresdto:ScoresDto[]){
        this.email = email;
        this.technology = technology;
        this.assessmentDate = assessmentDate;
        this.questionLevel = questionLevel;
        this.noOfQuestions = noOfQuestions;
        this.scoresdto = scoresdto;
    }
}
