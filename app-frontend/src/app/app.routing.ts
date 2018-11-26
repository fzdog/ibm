import { Routes, RouterModule } from '@angular/router';
import { MainMenuComponent } from "./main-menu/main-menu.component";
import { ClientListComponent } from './client-list/client-list.component';
import { BankingAdvisorComponent } from './banking-advisor/banking-advisor.component';
import { MainContentComponent } from './main-content/main-content.component';
import { UsageHistoryComponent } from './usage-history/usage-history.component';
import { CardComponent } from './card/card.component';

const appRoutes: Routes = [

    { path: '', redirectTo: '/app', pathMatch: 'full' },
    {
        path: 'app', component: MainMenuComponent,

        children: [
            {
                path: '',
                component: MainContentComponent
            },
            {
                path: 'client-list',
                component: ClientListComponent,
                children: [
                    {
                        path: 'cards/:id',
                        redirectTo: '/app/cards/:id',
                        pathMatch: 'full'
                    }
                ]

            },
            {
                path: 'banking-advisor',
                component: BankingAdvisorComponent
            },
            {
                path: 'cards/:id',
                component: CardComponent
            }




        ]
    },

]

export const appRoutingProviders: any[] = [RouterModule];
export const routing = RouterModule.forRoot(appRoutes);
