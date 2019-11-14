import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserExtend } from 'app/shared/model/user-extend.model';

@Component({
  selector: 'jhi-user-extend-detail',
  templateUrl: './user-extend-detail.component.html'
})
export class UserExtendDetailComponent implements OnInit {
  userExtend: IUserExtend;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userExtend }) => {
      this.userExtend = userExtend;
    });
  }

  previousState() {
    window.history.back();
  }
}
