import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskuniteSharedModule } from 'app/shared/shared.module';
import { MasterComponent } from './master.component';
import { MasterDetailComponent } from './master-detail.component';
import { MasterUpdateComponent } from './master-update.component';
import { MasterDeletePopupComponent, MasterDeleteDialogComponent } from './master-delete-dialog.component';
import { masterRoute, masterPopupRoute } from './master.route';

const ENTITY_STATES = [...masterRoute, ...masterPopupRoute];

@NgModule({
  imports: [TaskuniteSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MasterComponent, MasterDetailComponent, MasterUpdateComponent, MasterDeleteDialogComponent, MasterDeletePopupComponent],
  entryComponents: [MasterDeleteDialogComponent]
})
export class TaskuniteMasterModule {}
