import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskUniteSharedModule } from 'app/shared/shared.module';
import { UserExtendComponent } from './user-extend.component';
import { UserExtendDetailComponent } from './user-extend-detail.component';
import { UserExtendUpdateComponent } from './user-extend-update.component';
import { UserExtendDeleteDialogComponent } from './user-extend-delete-dialog.component';
import { userExtendRoute } from './user-extend.route';

@NgModule({
  imports: [TaskUniteSharedModule, RouterModule.forChild(userExtendRoute)],
  declarations: [UserExtendComponent, UserExtendDetailComponent, UserExtendUpdateComponent, UserExtendDeleteDialogComponent],
  entryComponents: [UserExtendDeleteDialogComponent]
})
export class TaskUniteUserExtendModule {}
