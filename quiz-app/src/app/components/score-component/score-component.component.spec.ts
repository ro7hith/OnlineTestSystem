import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ScoreComponentComponent } from './score-component.component';
import { provideRouter } from '@angular/router';
import { QuizserviceService } from '../../services/quizservice.service';

describe('ScoreComponent', () => {
  let component: ScoreComponentComponent;
  let fixture: ComponentFixture<ScoreComponentComponent>;
  let mockQuizService: jasmine.SpyObj<QuizserviceService>;

  beforeEach(async () => {
    mockQuizService = jasmine.createSpyObj('QuizService', ['getUserScore', 'getTotalScore']);
    mockQuizService.getUserScore.and.returnValue(8);
    mockQuizService.getTotalScore.and.returnValue(10);

    await TestBed.configureTestingModule({
      declarations: [ScoreComponentComponent],
      providers: [
        { provide: QuizserviceService, useValue: mockQuizService },
        provideRouter([]) 
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScoreComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display correct user score', () => {
    expect(component.userScore).toBe(8);
    expect(component.totalScore).toBe(10);
  });
});
