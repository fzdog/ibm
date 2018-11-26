import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from "@angular/http";
import { map } from 'rxjs/operators';
import { environment } from "../../environments/environment";
import { BankingAdvisor } from "./banking-advisor";

@Injectable()
export class BankingAdvisorService {

    public url: String;

    constructor(private _http: Http) {
        this.url = environment.backendUrl;
    }

    insertBankingAdvisor(bankingAdvisor: BankingAdvisor) {
        let json = JSON.stringify(bankingAdvisor);
        let parametros = json;

        let headers = new Headers({ 'Content-Type': 'application/json' });
        return this._http.post(this.url + 'bankingadvisorservice/insertBankingAdvisor', parametros, { headers: headers })
            .pipe(map(res =>
                res.json()));
    }

    updateBankingAdvisor(bankingAdvisor: BankingAdvisor) {
        let json = JSON.stringify(bankingAdvisor);
        let parametros = json;

        let headers = new Headers({ 'Content-Type': 'application/json' });
        return this._http.put(this.url + 'bankingadvisorservice/updateBankingAdvisor', parametros, { headers: headers })
            .pipe(map(res =>
                res.json()));
    }

    deleteBankingAdvisor(id: String) {

        let headers = new Headers({ 'Content-Type': 'application/json' })

        let opciones = new RequestOptions({ headers: headers });

        return this._http.delete(this.url + 'bankingadvisorservice/deleteBankingAdvisor/' + id, opciones)
            .pipe(map(res => res.json()));
    }


    listAllBankingAdvisorsByClientId() {
        return this._http.get(this.url + 'bankingadvisorservice/getAllBankingAdvisor')
            .pipe(map(res => res.json()));
    }

    getBankingAdvisorById(id: String) {
        return this._http.get(this.url + 'bankingadvisorservice/getBankingAdvisorById/' + id)
            .pipe(map(res => res.json()));
    }


}
