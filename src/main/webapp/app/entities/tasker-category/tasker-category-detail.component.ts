import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITaskerCategory } from 'app/shared/model/tasker-category.model';

@Component({
  selector: 'jhi-tasker-category-detail',
  templateUrl: './tasker-category-detail.component.html'
})
export class TaskerCategoryDetailComponent implements OnInit {
  taskerCategory: ITaskerCategory;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ taskerCategory }) => {
      this.taskerCategory = taskerCategory;
    });
  }

  previousState() {
    window.history.back();
  }
}
