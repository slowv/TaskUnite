import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMaster } from 'app/shared/model/master.model';
import { MasterService } from './master.service';

@Component({
  selector: 'jhi-master-delete-dialog',
  templateUrl: './master-delete-dialog.component.html'
})
export class MasterDeleteDialogComponent {
  master: IMaster;

  constructor(protected masterService: MasterService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.masterService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'masterListModification',
        content: 'Deleted an master'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-master-delete-popup',
  template: ''
})
export class MasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ master }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.master = master;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/master', { outlets: { popup: null } }]);
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
