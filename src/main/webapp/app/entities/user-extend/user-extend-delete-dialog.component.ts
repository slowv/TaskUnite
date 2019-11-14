import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserExtend } from 'app/shared/model/user-extend.model';
import { UserExtendService } from './user-extend.service';

@Component({
  selector: 'jhi-user-extend-delete-dialog',
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
    this.userExtendService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'userExtendListModification',
        content: 'Deleted an userExtend'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-user-extend-delete-popup',
  template: ''
})
export class UserExtendDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userExtend }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(UserExtendDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.userExtend = userExtend;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/user-extend', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/user-extend', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
