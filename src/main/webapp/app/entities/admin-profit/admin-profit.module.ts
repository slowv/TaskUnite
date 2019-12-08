import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskUniteSharedModule } from 'app/shared/shared.module';
import { AdminProfitComponent } from './admin-profit.component';
import { AdminProfitDetailComponent } from './admin-profit-detail.component';
import { AdminProfitUpdateComponent } from './admin-profit-update.component';
import { AdminProfitDeleteDialogComponent } from './admin-profit-delete-dialog.component';
import { adminProfitRoute } from './admin-profit.route';

@NgModule({
  imports: [TaskUniteSharedModule, RouterModule.forChild(adminProfitRoute)],
  declarations: [AdminProfitComponent, AdminProfitDetailComponent, AdminProfitUpdateComponent, AdminProfitDeleteDialogComponent],
  entryComponents: [AdminProfitDeleteDialogComponent]
})
export class TaskUniteAdminProfitModule {}
