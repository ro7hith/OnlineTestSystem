export class ScoresDto {
    
   
    questionId: number;
    selectedOption : string;
    answer: string;

    constructor(questionId: number, selectedOption : string, answer:string){
        
        this.questionId = questionId;
        this.selectedOption = selectedOption;
        this.answer = answer;
    }
}
