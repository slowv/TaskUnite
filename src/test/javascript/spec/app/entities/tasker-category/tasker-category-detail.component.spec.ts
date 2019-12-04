import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaskUniteTestModule } from '../../../test.module';
import { TaskerCategoryDetailComponent } from 'app/entities/tasker-category/tasker-category-detail.component';
import { TaskerCategory } from 'app/shared/model/tasker-category.model';

describe('Component Tests', () => {
  describe('TaskerCategory Management Detail Component', () => {
    let comp: TaskerCategoryDetailComponent;
    let fixture: ComponentFixture<TaskerCategoryDetailComponent>;
    const route = ({ data: of({ taskerCategory: new TaskerCategory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [TaskerCategoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TaskerCategoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TaskerCategoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.taskerCategory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
