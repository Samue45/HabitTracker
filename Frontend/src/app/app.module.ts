import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule,  provideHttpClient, withFetch} from '@angular/common/http';
import { AppComponent } from './app.component';
import { HabitService } from './services/habit.service';



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ provideHttpClient(withFetch()),HabitService],
  bootstrap: [AppComponent]
})
export class AppModule { }
