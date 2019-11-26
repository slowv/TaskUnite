import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserExtend } from 'app/shared/model/user-extend.model';
import { UserExtendService } from './user-extend.service';

@Component({
  templateUrl: './user-extend-delete-dialog.component.html'
})
export class UserExtendDeleteDialogComponent {
  userExtend: IUserExtend;

  constructor(
    protected userExtendService: UserExtendService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.userExtendService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'userExtendListModification',
        content: 'Deleted an userExtend'
      });
      this.activeModal.dismiss(true);
    });
  }
}
