import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from "@angular/http";
import { map } from 'rxjs/operators';
import { environment } from "../../environments/environment";
import { UsageHistory } from "./usage-history";

@Injectable()
export class UsageHistoryService {

  public url: String;

  constructor(private _http: Http) {
      this.url = environment.backendUrl;
  }
/*

  insertClient(client: Client) {
      let json = JSON.stringify(client);
      let parametros = json;
      
      let headers = new Headers({ 'Content-Type': 'application/json'});        
      return this._http.post(this.url + 'clientservice/insertClient', parametros, { headers: headers })
          .pipe(map(res =>                        
            res.json()));
  }

  updateClient(client: Client) {
    let json = JSON.stringify(client);
      let parametros = json;
      console.log("Editing");
      
      let headers = new Headers({ 'Content-Type': 'application/json'});        
      return this._http.put(this.url + 'clientservice/updateClient', parametros, { headers: headers })
          .pipe(map(res =>                        
            res.json()));
  }

  deleteClient(id: String) {

      let headers = new Headers({ 'Content-Type': 'application/json'})      

      let opciones = new RequestOptions({ headers: headers });
      
      return this._http.delete(this.url + 'clientservice/deleteClient/' + id, opciones)
          .pipe(map(res => res.json()));
  }


  listAllClients() {
      return this._http.get(this.url + 'clientservice/getAllClients')
          .pipe(map(res => res.json()));
  }

  getClientById(id:String){
    return this._http.get(this.url + 'clientservice/getClient/'+id)
          .pipe(map(res => res.json()));
  }

*/
}
