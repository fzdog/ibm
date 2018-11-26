import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
/**Providers y Servicios */
import { HttpModule } from '@angular/http';
import { ClientListService } from './client-list/client-list.service';

/**Componentes */
import { AppComponent } from './app.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { routing, appRoutingProviders } from "./app.routing";
import { ClientListComponent } from './client-list/client-list.component';
import { UsageHistoryComponent } from './usage-history/usage-history.component';
import { BankingAdvisorComponent } from './banking-advisor/banking-advisor.component';
import { MainContentComponent } from './main-content/main-content.component';


import { NewClientListComponent } from './client-list/new-client-list.component';
import { CardComponent } from './card/card.component';
import { UsageHistoryService } from './usage-history/usage-history.service';
import { CardService } from './card/card.service';
import { NewCardComponent } from './card/new-card.component';
import { NewBankingAdvisorComponent } from './banking-advisor/new-banking-advisor.component';
import { BankingAdvisorService } from './banking-advisor/banking-advisor.service';

@NgModule({
  declarations: [
    AppComponent,
    MainMenuComponent, ClientListComponent, UsageHistoryComponent, BankingAdvisorComponent, MainContentComponent, NewClientListComponent, CardComponent,
    CardComponent,NewCardComponent, NewBankingAdvisorComponent,BankingAdvisorComponent
  ],
  imports: [
    BrowserModule, routing, HttpModule, FormsModule, ReactiveFormsModule
  ],
  providers: [appRoutingProviders, ClientListService, UsageHistoryService, CardService,BankingAdvisorService],
  bootstrap: [AppComponent,]
})
export class AppModule { }
