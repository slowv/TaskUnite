import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaskUniteTestModule } from '../../../test.module';
import { UserExtendDetailComponent } from 'app/entities/user-extend/user-extend-detail.component';
import { UserExtend } from 'app/shared/model/user-extend.model';

describe('Component Tests', () => {
  describe('UserExtend Management Detail Component', () => {
    let comp: UserExtendDetailComponent;
    let fixture: ComponentFixture<UserExtendDetailComponent>;
    const route = ({ data: of({ userExtend: new UserExtend(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [UserExtendDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UserExtendDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserExtendDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userExtend).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
