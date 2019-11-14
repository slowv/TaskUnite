import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskUniteSharedModule } from 'app/shared/shared.module';
import { UserExtendComponent } from './user-extend.component';
import { UserExtendDetailComponent } from './user-extend-detail.component';
import { UserExtendUpdateComponent } from './user-extend-update.component';
import { UserExtendDeletePopupComponent, UserExtendDeleteDialogComponent } from './user-extend-delete-dialog.component';
import { userExtendRoute, userExtendPopupRoute } from './user-extend.route';

const ENTITY_STATES = [...userExtendRoute, ...userExtendPopupRoute];

@NgModule({
  imports: [TaskUniteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    UserExtendComponent,
    UserExtendDetailComponent,
    UserExtendUpdateComponent,
    UserExtendDeleteDialogComponent,
    UserExtendDeletePopupComponent
  ],
  entryComponents: [UserExtendDeleteDialogComponent]
})
export class TaskUniteUserExtendModule {}
