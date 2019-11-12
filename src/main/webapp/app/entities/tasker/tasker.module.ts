import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskuniteSharedModule } from 'app/shared/shared.module';
import { TaskerComponent } from './tasker.component';
import { TaskerDetailComponent } from './tasker-detail.component';
import { TaskerUpdateComponent } from './tasker-update.component';
import { TaskerDeletePopupComponent, TaskerDeleteDialogComponent } from './tasker-delete-dialog.component';
import { taskerRoute, taskerPopupRoute } from './tasker.route';

const ENTITY_STATES = [...taskerRoute, ...taskerPopupRoute];

@NgModule({
  imports: [TaskuniteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [TaskerComponent, TaskerDetailComponent, TaskerUpdateComponent, TaskerDeleteDialogComponent, TaskerDeletePopupComponent],
  entryComponents: [TaskerDeleteDialogComponent]
})
export class TaskuniteTaskerModule {}
