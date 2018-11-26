import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';
import { CardService } from './card.service';
import { Card } from './card';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ClientListService } from '../client-list/client-list.service';
import { Client } from '../client-list/client';

declare var jquery: any;
declare var $: any;

@Component({
  selector: 'card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  isEditing: Boolean = false;
  clientId: String = "";
  onSaved = new EventEmitter<Boolean>();
  serviceSubscription: Subscription;
  cards: Array<Card> = new Array();
  mensajeError: String = "";
  mensajeResultado: String = "";
  mostrarMensajeOk: Boolean = false;
  mostrarMensajeError: Boolean = false;
  client: Client = new Client();
  cardId:String ="";

  constructor(private cardService: CardService, private router: Router, private route: ActivatedRoute, private clientService: ClientListService) { }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      this.clientId = params['id'];
      this.serviceSubscription = this.clientService.getClientById(this.clientId).subscribe(response => {
        this.client = response;

      });
      console.log('Client Id params:', this.clientId);

    })
    this.getCards();
  }


  saved(isSaved) {

    if (isSaved) {
      this.serviceSubscription.unsubscribe();
      console.log(this.clientId);
      this.getCards();
      $('#createModal').modal('hide');
      this.mensaje(false, "Registro guardado con éxito");
      this.isEditing = false;
    } else {
      this.mensaje(true, "Error al guardar Registro");
    }


  }

  getCards() {
    this.serviceSubscription = this.cardService.listAllCardsByClientId(this.clientId).subscribe(response => {

      this.cards = Array.from(response);
    }, error => {
      console.log(error);
      this.mensaje(true, "Error al listar clientes")
    })

  }

  deleteRow() {
    $('#deleteModal').modal('hide');
    this.serviceSubscription = this.cardService.deleteCard(this.cardId).subscribe(response => {
      if (response != "success") {
        this.mensaje(true, "Error al eliminar registro")
      } else {
        this.mensaje(false, "Registro eliminado ");
        this.getCards();
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

  selectObject(card: Card) {
    this.cardId = card.id;

  }

  onEdit(card: Card) {
    this.cardId = card.id;
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
