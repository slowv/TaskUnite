import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITaskerCategory } from 'app/shared/model/tasker-category.model';
import { TaskerCategoryService } from './tasker-category.service';

@Component({
  templateUrl: './tasker-category-delete-dialog.component.html'
})
export class TaskerCategoryDeleteDialogComponent {
  taskerCategory: ITaskerCategory;

  constructor(
    protected taskerCategoryService: TaskerCategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.taskerCategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'taskerCategoryListModification',
        content: 'Deleted an taskerCategory'
      });
      this.activeModal.dismiss(true);
    });
  }
}
