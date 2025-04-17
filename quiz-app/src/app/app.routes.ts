import { Routes } from '@angular/router';
import { ScoreCardComponent } from './components/scorecard/scorecard.component';
import { QuestionsComponent } from './components/questions/questions.component';
import { StartquizComponent } from './components/startquiz/startquiz.component';
import { QuizSetupComponent } from './components/quiz-setup/quiz-setup.component';
import { AdminComponent } from './components/admin/admin.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';
import { DetailsComponent } from './components/details/details.component';
import { RulesComponent } from './rules/rules.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'user', component: UserComponent },
  { path: 'logout', component: LogoutComponent },
  { path: "scorecard/:assessmentId", component: ScoreCardComponent },
  { path: "setup", component: QuizSetupComponent },
  { path: 'rules', component: RulesComponent },
  { path: "beginQuiz", component: StartquizComponent },
  { path: "questions", component: QuestionsComponent },
  { path: 'homepage', component: HomepageComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'change-password', component: ChangePasswordComponent },
   { path: 'details/:assessmentId', component: DetailsComponent }
];
