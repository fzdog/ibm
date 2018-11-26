import { Component, OnInit, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';
import { BankingAdvisor } from './banking-advisor';
import { BankingAdvisorService } from './banking-advisor.service';
declare var $: any;

@Component({
  selector: 'app-banking-advisor',
  templateUrl: './banking-advisor.component.html',
  styleUrls: ['./banking-advisor.component.css']
})
export class BankingAdvisorComponent implements OnInit {

  isEditing: Boolean = false;  
  onSaved = new EventEmitter<Boolean>();
  serviceSubscription: Subscription;
  bankingAdvisors: Array<BankingAdvisor> = new Array();
  mensajeError: String = "";
  mensajeResultado: String = "";
  mostrarMensajeOk: Boolean = false;
  mostrarMensajeError: Boolean = false;
  bankingAdvisor: BankingAdvisor = new BankingAdvisor();
  bankingAdvisorId:String ="";

  constructor(private bankingAdvisorService: BankingAdvisorService) { }

  ngOnInit() {
  
    this.getBankAdvisors();
  }

  saved(isSaved) {

    if (isSaved) {
      this.serviceSubscription.unsubscribe();      
      this.getBankAdvisors();
      $('#createModal').modal('hide');
      this.mensaje(false, "Registro guardado con éxito");
      this.isEditing = false;
    } else {
      this.mensaje(true, "Error al guardar Registro");
    }

  }

  getBankAdvisors() {
    this.serviceSubscription = this.bankingAdvisorService.listAllBankingAdvisorsByClientId().subscribe(response => {

      this.bankingAdvisors = Array.from(response);
    }, error => {
      console.log(error);
      this.mensaje(true, "Error al listar clientes")
    })

  }

  deleteRow() {
    $('#deleteModal').modal('hide');
    this.serviceSubscription = this.bankingAdvisorService.deleteBankingAdvisor(this.bankingAdvisorId).subscribe(response => {
      if (response != "success") {
        this.mensaje(true, "Error al eliminar registro")
      } else {
        this.mensaje(false, "Registro eliminado ");
        this.getBankAdvisors();
      }

    }, error => {
      console.log(error);
      this.mensaje(true, "Error al eliminar registro")
    })

  }

  createNew() {
    $('#createModal').modal('show');
    this.isEditing = false;
  }

  closeDeleteModal() {
    $('#deleteModal').modal('hide');
  }

  selectObject(bankingAdvisor: BankingAdvisor) {
    this.bankingAdvisorId = bankingAdvisor.id;

  }

  onEdit(bankingAdvisor: BankingAdvisor) {
    this.bankingAdvisorId = bankingAdvisor.id;
    this.isEditing = true;
    $('#createModal').modal('show');
  }

  mensaje(error: Boolean, mensaje: String) {
    if (error) {
      this.mostrarMensajeError = true;
    } else {
      this.mostrarMensajeOk = true;
    }
    this.mensajeResultado = mensaje;
  }

  ocultarMensaje() {
    this.mostrarMensajeError = false;
    this.mostrarMensajeOk = false;
    this.mensajeResultado = "";
  }

}
