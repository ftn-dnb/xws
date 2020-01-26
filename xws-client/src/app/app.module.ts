import { AddTokenInterceptor } from './config/http-interceptor';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { AccountConfirmationComponent } from './components/account-confirmation/account-confirmation.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { MyPublicationsComponent } from './components/my-publications/my-publications.component';
import { PublicationComponent } from './components/publication/publication.component';
import { BusinessProcessComponent } from './components/business-process/business-process.component';
import { BusinessProcessDetailsComponent } from './components/business-process-details/business-process-details.component';
import { AddPublicationComponent } from './components/add-publication/add-publication.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    HomeComponent,
    AccountConfirmationComponent,
    LoginComponent,
    RegistrationComponent,
    MyPublicationsComponent,
    PublicationComponent,
    BusinessProcessComponent,
    BusinessProcessDetailsComponent,
    AddPublicationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AddTokenInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
