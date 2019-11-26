import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'user-extend',
        loadChildren: () => import('./user-extend/user-extend.module').then(m => m.TaskUniteUserExtendModule)
      },
      {
        path: 'tasker',
        loadChildren: () => import('./tasker/tasker.module').then(m => m.TaskUniteTaskerModule)
      },
      {
        path: 'master',
        loadChildren: () => import('./master/master.module').then(m => m.TaskUniteMasterModule)
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
        path: 'address',
        loadChildren: () => import('./address/address.module').then(m => m.TaskUniteAddressModule)
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
        path: 'message',
        loadChildren: () => import('./message/message.module').then(m => m.TaskUniteMessageModule)
      },
      {
        path: 'payment-information',
        loadChildren: () => import('./payment-information/payment-information.module').then(m => m.TaskUnitePaymentInformationModule)
      },
      {
        path: 'statistic',
        loadChildren: () => import('./statistic/statistic.module').then(m => m.TaskUniteStatisticModule)
      },
      {
        path: 'schedule',
        loadChildren: () => import('./schedule/schedule.module').then(m => m.TaskUniteScheduleModule)
      },
      {
        path: 'plan',
        loadChildren: () => import('./plan/plan.module').then(m => m.TaskUnitePlanModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class TaskUniteEntityModule {}
