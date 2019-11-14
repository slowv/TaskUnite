import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TaskUniteTestModule } from '../../../test.module';
import { UserExtendDeleteDialogComponent } from 'app/entities/user-extend/user-extend-delete-dialog.component';
import { UserExtendService } from 'app/entities/user-extend/user-extend.service';

describe('Component Tests', () => {
  describe('UserExtend Management Delete Component', () => {
    let comp: UserExtendDeleteDialogComponent;
    let fixture: ComponentFixture<UserExtendDeleteDialogComponent>;
    let service: UserExtendService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [UserExtendDeleteDialogComponent]
      })
        .overrideTemplate(UserExtendDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserExtendDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserExtendService);
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
