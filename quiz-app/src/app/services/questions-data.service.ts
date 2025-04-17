import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionsDataService {
  private questions: any[] = [];
  private technology!: string;
  private difficulty!: string;

  setQuestions(questions: any[]) {
    this.questions = questions;
  }

  getQuestions(): any[] {
    return this.questions;
  }

  setTechnology(tech: string) {
    this.technology = tech;
  }

  getTechnology(): string {
    return this.technology;
  }

  setDifficulty(level: string) {
    this.difficulty = level;
  }

  getDifficulty(): string {
    return this.difficulty;
  }
}
