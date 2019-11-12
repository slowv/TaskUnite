import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITasker } from 'app/shared/model/tasker.model';
import { TaskerService } from './tasker.service';

@Component({
  selector: 'jhi-tasker-delete-dialog',
  templateUrl: './tasker-delete-dialog.component.html'
})
export class TaskerDeleteDialogComponent {
  tasker: ITasker;

  constructor(protected taskerService: TaskerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.taskerService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'taskerListModification',
        content: 'Deleted an tasker'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tasker-delete-popup',
  template: ''
})
export class TaskerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tasker }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TaskerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.tasker = tasker;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/tasker', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/tasker', { outlets: { popup: null } }]);
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
