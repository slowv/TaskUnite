import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TaskUniteTestModule } from '../../../test.module';
import { TaskerCategoryDeleteDialogComponent } from 'app/entities/tasker-category/tasker-category-delete-dialog.component';
import { TaskerCategoryService } from 'app/entities/tasker-category/tasker-category.service';

describe('Component Tests', () => {
  describe('TaskerCategory Management Delete Component', () => {
    let comp: TaskerCategoryDeleteDialogComponent;
    let fixture: ComponentFixture<TaskerCategoryDeleteDialogComponent>;
    let service: TaskerCategoryService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [TaskerCategoryDeleteDialogComponent]
      })
        .overrideTemplate(TaskerCategoryDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TaskerCategoryDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TaskerCategoryService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
