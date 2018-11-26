import { Component, OnInit } from '@angular/core';
import { ClientListService } from './client-list.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Client } from './client';

declare var jquery: any;
declare var $: any;

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  serviceSubscription: Subscription;
  mensajeError: String = "";
  mensajeResultado: String = "";
  mostrarMensajeOk: Boolean = false;
  mostrarMensajeError: Boolean = false;
  isEditing:Boolean=false;
  clients: Array<Client> = new Array();
  clientId: String;

  constructor(private clientService: ClientListService, private router: Router) {
  }

  ngOnInit() {
    this.getClients();
  }

  saved(isSaved) {

    if (isSaved) {
      this.serviceSubscription.unsubscribe();
      this.getClients();
      $('#createModal').modal('hide');
      this.mensaje(false, "Registro guardado con éxito");
      this.isEditing = false;
    } else {
      this.mensaje(true, "Error al guardar Registro");
    }


  }

  getClients() {
    this.serviceSubscription = this.clientService.listAllClients().subscribe(response => {

      this.clients = Array.from(response);
    }, error => {
      console.log(error);
      this.mensaje(true, "Error al listar clientes")
    })

  }

  deleteClient() {
    $('#deleteModal').modal('hide');
    this.serviceSubscription = this.clientService.deleteClient( this.clientId).subscribe(response => {
      if(response !="success"){
        this.mensaje(true, "Error al eliminar registro")
      }else{
        this.mensaje(false, "Registro eliminado ");
        this.getClients();
      }
      
    }, error => {
      console.log(error);
      this.mensaje(true, "Error al eliminar registro")
    })
    
  }

  createNew(){
    $('#createModal').modal('show');
    this.isEditing = false;
  }

  closeDeleteModal() {
    $('#deleteModal').modal('hide');
  }

  selectObject(client: Client) {
    this.clientId = client.id;

  }

  onEdit(client: Client){
    this.clientId = client.id;    
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
