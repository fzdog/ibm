import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from "@angular/http";
import { map } from 'rxjs/operators';
import { environment } from "../../environments/environment";
import { Card } from "./card";

@Injectable()
export class CardService {

    public url: String;

    constructor(private _http: Http) {
        this.url = environment.backendUrl;
    }

    insertCard(card: Card) {
        let json = JSON.stringify(card);
        let parametros = json;

        let headers = new Headers({ 'Content-Type': 'application/json' });
        return this._http.post(this.url + 'cardservice/insertCard', parametros, { headers: headers })
            .pipe(map(res =>
                res.json()));
    }

    updateCard(card: Card) {
        let json = JSON.stringify(card);
        let parametros = json;

        let headers = new Headers({ 'Content-Type': 'application/json' });
        return this._http.put(this.url + 'cardservice/updateCard', parametros, { headers: headers })
            .pipe(map(res =>
                res.json()));
    }

    deleteCard(id: String) {

        let headers = new Headers({ 'Content-Type': 'application/json' })

        let opciones = new RequestOptions({ headers: headers });

        return this._http.delete(this.url + 'cardservice/deleteCard/' + id, opciones)
            .pipe(map(res => res.json()));
    }


    listAllCardsByClientId(id: String) {
        return this._http.get(this.url + 'cardservice/getAllCardsByClient/' + id)
            .pipe(map(res => res.json()));
    }

    getCardById(id: String) {
        return this._http.get(this.url + 'cardservice/getCardById/' + id)
            .pipe(map(res => res.json()));
    }


}
