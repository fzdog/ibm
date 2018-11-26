import { Component, OnInit, Input, Output, EventEmitter, SimpleChanges, OnChanges } from '@angular/core';
import { Client } from './client';
import { NgForm, FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ClientListService } from './client-list.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'new-client-list',
  templateUrl: './new-client-list.component.html',
  styleUrls: ['./new-client-list.component.css']
})
export class NewClientListComponent implements OnInit, OnChanges {

  @Input() isEditing: Boolean = false;
  @Input() clientId: String = "";
  @Output() onSaved = new EventEmitter<Boolean>();

  dataForm: FormGroup;
  client: Client = new Client();
  showOkMessage: Boolean = false;
  showErrorMessage: Boolean = false;
  errorMessage: String = "";
  resultMessage: String = "";
  serviceSubscription: Subscription;

  constructor(private fb: FormBuilder, private clientService: ClientListService) { }

  ngOnInit() {
    this.client = new Client();
    this.loadForm();
  }

  ngOnChanges(changes: SimpleChanges) {

    this.ocultarMensaje();
    if (this.clientId && this.isEditing) {      
      this.isEditing = true;
      this.serviceSubscription = this.clientService.getClientById(this.clientId).subscribe(response => {
        this.client = response;
        
      });
    }else{
      this.client = new Client();
    }
  }

  loadForm() {
    this.dataForm = this.fb.group({
      name: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      address: new FormControl('', [Validators.required, Validators.maxLength(100)]),
      city: new FormControl('', [Validators.required, Validators.maxLength(30)]),
      phone: new FormControl('', [Validators.required, Validators.maxLength(20)])
    })
  }

  onSave(form: NgForm) {
    if (form.valid) {

      this.ocultarMensaje();

      if (this.isEditing) {

        this.serviceSubscription = this.clientService.updateClient(this.client).subscribe(response => {

          if (response != "success") {
            this.message(true, response.message);
          } else {            
            this.client = new Client();
            this.onSaved.emit(true);
          }
        });

      } else {
        this.serviceSubscription = this.clientService.insertClient(this.client).subscribe(response => {

          if (response != "success") {
            this.message(true, response.message);
          } else {
            
            this.client = new Client();
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
