import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Client } from '../client-list/client';
import { Subscription } from 'rxjs';
import { ClientListService } from '../client-list/client-list.service';

@Component({
  selector: 'app-usage-history',
  templateUrl: './usage-history.component.html',
  styleUrls: ['./usage-history.component.css']
})
export class UsageHistoryComponent implements OnInit {

  client:Client=new Client();  
  serviceSubscription: Subscription;
  clientId: String = "";

  constructor( ) { }

  ngOnInit() {
    
  }

}
