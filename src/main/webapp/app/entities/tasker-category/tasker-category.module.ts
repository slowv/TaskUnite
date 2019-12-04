import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskUniteSharedModule } from 'app/shared/shared.module';
import { TaskerCategoryComponent } from './tasker-category.component';
import { TaskerCategoryDetailComponent } from './tasker-category-detail.component';
import { TaskerCategoryUpdateComponent } from './tasker-category-update.component';
import { TaskerCategoryDeleteDialogComponent } from './tasker-category-delete-dialog.component';
import { taskerCategoryRoute } from './tasker-category.route';

@NgModule({
  imports: [TaskUniteSharedModule, RouterModule.forChild(taskerCategoryRoute)],
  declarations: [
    TaskerCategoryComponent,
    TaskerCategoryDetailComponent,
    TaskerCategoryUpdateComponent,
    TaskerCategoryDeleteDialogComponent
  ],
  entryComponents: [TaskerCategoryDeleteDialogComponent]
})
export class TaskUniteTaskerCategoryModule {}
