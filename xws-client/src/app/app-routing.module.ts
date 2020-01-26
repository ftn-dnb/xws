import { AddPublicationComponent } from './components/add-publication/add-publication.component';
import { BusinessProcessDetailsComponent } from './components/business-process-details/business-process-details.component';
import { BusinessProcessComponent } from './components/business-process/business-process.component';
import { PublicationComponent } from './components/publication/publication.component';
import { MyPublicationsComponent } from './components/my-publications/my-publications.component';
import { AccountConfirmationComponent } from './components/account-confirmation/account-confirmation.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { HOME_PATH, LOGIN_PATH, REGISTRATION_PATH, VERIFY_ACCOUNT_PATH, MY_PUBLICATIONS_PATH, PUBLICATION_PATH, BUSINESS_PROCESSES_PATH, BUSINESS_PROCESS_DETAILS_PATH, ADD_PUBLICATION_PATH } from './config/router-paths';
import { HomeComponent } from './components/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: HOME_PATH, component: HomeComponent },
  { path: LOGIN_PATH, component: LoginComponent },
  { path: REGISTRATION_PATH, component: RegistrationComponent },
  { path: VERIFY_ACCOUNT_PATH, component: AccountConfirmationComponent },
  { path: MY_PUBLICATIONS_PATH, component: MyPublicationsComponent },
  { path: PUBLICATION_PATH, component: PublicationComponent },
  { path: BUSINESS_PROCESSES_PATH, component: BusinessProcessComponent },
  { path: BUSINESS_PROCESS_DETAILS_PATH, component: BusinessProcessDetailsComponent },
  { path: ADD_PUBLICATION_PATH, component: AddPublicationComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
