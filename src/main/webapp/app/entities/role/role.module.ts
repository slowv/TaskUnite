import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskuniteSharedModule } from 'app/shared/shared.module';
import { RoleComponent } from './role.component';
import { RoleDetailComponent } from './role-detail.component';
import { RoleUpdateComponent } from './role-update.component';
import { RoleDeletePopupComponent, RoleDeleteDialogComponent } from './role-delete-dialog.component';
import { roleRoute, rolePopupRoute } from './role.route';

const ENTITY_STATES = [...roleRoute, ...rolePopupRoute];

@NgModule({
  imports: [TaskuniteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [RoleComponent, RoleDetailComponent, RoleUpdateComponent, RoleDeleteDialogComponent, RoleDeletePopupComponent],
  entryComponents: [RoleDeleteDialogComponent]
})
export class TaskuniteRoleModule {}
