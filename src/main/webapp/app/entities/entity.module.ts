import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'notification',
        loadChildren: () => import('./notification/notification.module').then(m => m.TaskuniteNotificationModule)
      },
      {
        path: 'review',
        loadChildren: () => import('./review/review.module').then(m => m.TaskuniteReviewModule)
      },
      {
        path: 'message',
        loadChildren: () => import('./message/message.module').then(m => m.TaskuniteMessageModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class TaskuniteEntityModule {}
