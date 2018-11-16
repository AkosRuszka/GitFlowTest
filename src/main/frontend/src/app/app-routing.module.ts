import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { BlogpostComponent } from './blogpost/blogpost.component';
import { BloggerComponent } from './blogger/blogger.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: "" , component: HomeComponent },
  { path: "blogposts", component: BlogpostComponent },
  { path: "bloggers" , component: BloggerComponent },
  { path: "login" , component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
