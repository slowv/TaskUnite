import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskuniteSharedModule } from 'app/shared/shared.module';
import { TaskCategoryComponent } from './task-category.component';
import { TaskCategoryDetailComponent } from './task-category-detail.component';
import { TaskCategoryUpdateComponent } from './task-category-update.component';
import { TaskCategoryDeletePopupComponent, TaskCategoryDeleteDialogComponent } from './task-category-delete-dialog.component';
import { taskCategoryRoute, taskCategoryPopupRoute } from './task-category.route';

const ENTITY_STATES = [...taskCategoryRoute, ...taskCategoryPopupRoute];

@NgModule({
  imports: [TaskuniteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TaskCategoryComponent,
    TaskCategoryDetailComponent,
    TaskCategoryUpdateComponent,
    TaskCategoryDeleteDialogComponent,
    TaskCategoryDeletePopupComponent
  ],
  entryComponents: [TaskCategoryDeleteDialogComponent]
})
export class TaskuniteTaskCategoryModule {}
