import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMessage } from 'app/shared/model/message.model';
import { MessageService } from './message.service';

@Component({
  templateUrl: './message-delete-dialog.component.html'
})
export class MessageDeleteDialogComponent {
  message: IMessage;

  constructor(protected messageService: MessageService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.messageService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'messageListModification',
        content: 'Deleted an message'
      });
      this.activeModal.dismiss(true);
    });
  }
}
