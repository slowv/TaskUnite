import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITaskCategory } from 'app/shared/model/task-category.model';
import { TaskCategoryService } from './task-category.service';

@Component({
  selector: 'jhi-task-category-delete-dialog',
  templateUrl: './task-category-delete-dialog.component.html'
})
export class TaskCategoryDeleteDialogComponent {
  taskCategory: ITaskCategory;

  constructor(
    protected taskCategoryService: TaskCategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.taskCategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'taskCategoryListModification',
        content: 'Deleted an taskCategory'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-task-category-delete-popup',
  template: ''
})
export class TaskCategoryDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ taskCategory }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TaskCategoryDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.taskCategory = taskCategory;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/task-category', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/task-category', { outlets: { popup: null } }]);
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
