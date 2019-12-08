import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TaskUniteSharedModule } from 'app/shared/shared.module';
import { AdminTransactionComponent } from './admin-transaction.component';
import { AdminTransactionDetailComponent } from './admin-transaction-detail.component';
import { AdminTransactionUpdateComponent } from './admin-transaction-update.component';
import { AdminTransactionDeleteDialogComponent } from './admin-transaction-delete-dialog.component';
import { adminTransactionRoute } from './admin-transaction.route';

@NgModule({
  imports: [TaskUniteSharedModule, RouterModule.forChild(adminTransactionRoute)],
  declarations: [
    AdminTransactionComponent,
    AdminTransactionDetailComponent,
    AdminTransactionUpdateComponent,
    AdminTransactionDeleteDialogComponent
  ],
  entryComponents: [AdminTransactionDeleteDialogComponent]
})
export class TaskUniteAdminTransactionModule {}
