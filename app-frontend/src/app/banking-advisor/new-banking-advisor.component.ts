import { Component, OnInit, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, NgForm } from '@angular/forms';
import { BankingAdvisor } from './banking-advisor';
import { Subscription } from 'rxjs';
import { BankingAdvisorService } from './banking-advisor.service';

@Component({
  selector: 'new-banking-advisor',
  templateUrl: './new-banking-advisor.component.html',
  styleUrls: ['./new-banking-advisor.component.css']
})
export class NewBankingAdvisorComponent implements OnInit,OnChanges {

  @Input() isEditing: Boolean = false;
  @Input() bankingAdvisorId: String = "";  
  @Output() onSaved = new EventEmitter<Boolean>();

  dataForm: FormGroup;
  bankingAdvisor: BankingAdvisor = new BankingAdvisor();
  showOkMessage: Boolean = false;
  showErrorMessage: Boolean = false;
  errorMessage: String = "";
  resultMessage: String = "";
  serviceSubscription: Subscription;

  constructor(private fb: FormBuilder, private bankingAdvisorService: BankingAdvisorService) { }

  ngOnInit() {
      this.bankingAdvisor = new BankingAdvisor();
      this.loadForm();
  }

  ngOnChanges(changes: SimpleChanges) {      
      console.log(this.bankingAdvisorId);
      
      this.ocultarMensaje();
      if (this.bankingAdvisorId && this.isEditing) {
          this.isEditing = true;
          this.serviceSubscription = this.bankingAdvisorService.getBankingAdvisorById(this.bankingAdvisorId).subscribe(response => {
              this.bankingAdvisor = response;

          });
      } else {
          this.bankingAdvisor = new BankingAdvisor();
      }
  }

  loadForm() {
      this.dataForm = this.fb.group({
          name: new FormControl('', [Validators.required, Validators.maxLength(50)]),
          specialty: new FormControl('', [Validators.required, Validators.maxLength(50)]),          
      })
  }

  onSave(form: NgForm) {
    console.log(this.bankingAdvisor);
    
      if (form.valid) {

          this.ocultarMensaje();
          
          if (this.isEditing) {

              this.serviceSubscription = this.bankingAdvisorService.updateBankingAdvisor(this.bankingAdvisor).subscribe(response => {

                  if (response != "success") {
                      this.message(true, response.message);
                  } else {
                      this.bankingAdvisor = new BankingAdvisor();
                      this.onSaved.emit(true);
                  }
              });

          } else {
              this.serviceSubscription = this.bankingAdvisorService.insertBankingAdvisor(this.bankingAdvisor).subscribe(response => {

                  if (response != "success") {
                      this.message(true, response.message);
                  } else {

                      this.bankingAdvisor = new BankingAdvisor();
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
