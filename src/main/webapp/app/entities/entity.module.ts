import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'payment-information',
        loadChildren: () => import('./payment-information/payment-information.module').then(m => m.TaskUnitePaymentInformationModule)
      },
      {
        path: 'user-information',
        loadChildren: () => import('./user-information/user-information.module').then(m => m.TaskUniteUserInformationModule)
      },
      {
        path: 'statistic',
        loadChildren: () => import('./statistic/statistic.module').then(m => m.TaskUniteStatisticModule)
      },
      {
        path: 'task',
        loadChildren: () => import('./task/task.module').then(m => m.TaskUniteTaskModule)
      },
      {
        path: 'task-category',
        loadChildren: () => import('./task-category/task-category.module').then(m => m.TaskUniteTaskCategoryModule)
      },
      {
        path: 'city',
        loadChildren: () => import('./city/city.module').then(m => m.TaskUniteCityModule)
      },
      {
        path: 'district',
        loadChildren: () => import('./district/district.module').then(m => m.TaskUniteDistrictModule)
      },
      {
        path: 'notification',
        loadChildren: () => import('./notification/notification.module').then(m => m.TaskUniteNotificationModule)
      },
      {
        path: 'review',
        loadChildren: () => import('./review/review.module').then(m => m.TaskUniteReviewModule)
      },
      {
        path: 'tasker-category',
        loadChildren: () => import('./tasker-category/tasker-category.module').then(m => m.TaskUniteTaskerCategoryModule)
      },
      {
        path: 'admin-transaction',
        loadChildren: () => import('./admin-transaction/admin-transaction.module').then(m => m.TaskUniteAdminTransactionModule)
      },
      {
        path: 'admin-profit',
        loadChildren: () => import('./admin-profit/admin-profit.module').then(m => m.TaskUniteAdminProfitModule)
      },
      {
        path: 'message',
        loadChildren: () => import('./message/message.module').then(m => m.TaskUniteMessageModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class TaskUniteEntityModule {}
