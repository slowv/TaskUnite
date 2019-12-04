import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaskUniteTestModule } from '../../../test.module';
import { TaskerCategoryUpdateComponent } from 'app/entities/tasker-category/tasker-category-update.component';
import { TaskerCategoryService } from 'app/entities/tasker-category/tasker-category.service';
import { TaskerCategory } from 'app/shared/model/tasker-category.model';

describe('Component Tests', () => {
  describe('TaskerCategory Management Update Component', () => {
    let comp: TaskerCategoryUpdateComponent;
    let fixture: ComponentFixture<TaskerCategoryUpdateComponent>;
    let service: TaskerCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [TaskerCategoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TaskerCategoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TaskerCategoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TaskerCategoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TaskerCategory(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TaskerCategory();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
