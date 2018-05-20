import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { AppRoutingModule } from './/app-routing.module';
import { BesthorrorComponent } from './besthorror/besthorror.component';
import { SobaditsgoodComponent } from './sobaditsgood/sobaditsgood.component';
import { HomeComponent } from './home/home.component';
import {ROUTING} from './app.routing';
import {MovietitleapiService} from './movietitleapi.service';
import { HttpComponent } from './http/http.component';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    BesthorrorComponent,
    SobaditsgoodComponent,
    HomeComponent,
    HttpComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ROUTING,
    FormsModule
  ],
  providers: [MovietitleapiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
