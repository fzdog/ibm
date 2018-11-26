import { Component, OnInit, Input, Output, EventEmitter, SimpleChanges, OnChanges } from '@angular/core';
import { Card } from './card';
import { NgForm, FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CardService } from './card.service';
import { Subscription } from 'rxjs';

@Component({
    selector: 'new-card',
    templateUrl: './new-card.component.html'
})
export class NewCardComponent implements OnInit, OnChanges {

    @Input() isEditing: Boolean = false;
    @Input() cardId: String = "";
    @Input() clientId: String = "";
    @Output() onSaved = new EventEmitter<Boolean>();

    dataForm: FormGroup;
    card: Card = new Card();
    showOkMessage: Boolean = false;
    showErrorMessage: Boolean = false;
    errorMessage: String = "";
    resultMessage: String = "";
    serviceSubscription: Subscription;

    constructor(private fb: FormBuilder, private cardService: CardService) { }

    ngOnInit() {
        this.card = new Card();
        this.loadForm();
    }

    ngOnChanges(changes: SimpleChanges) {
        console.log(this.clientId);
        
        this.ocultarMensaje();
        if (this.cardId && this.isEditing) {
            this.isEditing = true;
            this.serviceSubscription = this.cardService.getCardById(this.cardId).subscribe(response => {
                this.card = response;

            });
        } else {
            this.card = new Card();
        }
    }

    loadForm() {
        this.dataForm = this.fb.group({
            cardNumber: new FormControl('', [Validators.required, Validators.maxLength(16)]),
            ccv: new FormControl('', [Validators.required, Validators.maxLength(4)]),
            type: new FormControl('', [Validators.required, Validators.maxLength(50)])
        })
    }

    onSave(form: NgForm) {
        if (form.valid) {

            this.ocultarMensaje();
            this.card.client_id=this.clientId;
            if (this.isEditing) {

                this.serviceSubscription = this.cardService.updateCard(this.card).subscribe(response => {

                    if (response != "success") {
                        this.message(true, response.message);
                    } else {
                        this.card = new Card();
                        this.onSaved.emit(true);
                    }
                });

            } else {
                this.serviceSubscription = this.cardService.insertCard(this.card).subscribe(response => {

                    if (response != "success") {
                        this.message(true, response.message);
                    } else {

                        this.card = new Card();
                        this.onSaved.emit(true);
                    }
                });


            }
        }
    }

    message(error: Boolean, message: String) {
        if (error) {
            this.showErrorMessage = true;
        } else {
            this.showOkMessage = true;
        }
        this.resultMessage = message;
    }

    ocultarMensaje() {
        this.showErrorMessage = false;
        this.showOkMessage = false;
        this.resultMessage = "";
    }

}
