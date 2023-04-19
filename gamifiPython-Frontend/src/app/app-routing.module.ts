import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExamOneComponent } from './exam-one/exam-one.component';
import { ExamTwoComponent } from './exam-two/exam-two.component';
import { LoginComponent } from './login/login.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { ModuleOneComponent } from './module-one/module-one.component';
import { ModuleTwoComponent } from './module-two/module-two.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'main', component: MainMenuComponent },
  { path: 'module/1', component: ModuleOneComponent },
  { path: 'module/2', component: ModuleTwoComponent },
  { path: 'exam/1', component: ExamOneComponent },
  { path: 'exam/2', component: ExamTwoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
