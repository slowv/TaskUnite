import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TaskUniteTestModule } from '../../../test.module';
import { AdminProfitDeleteDialogComponent } from 'app/entities/admin-profit/admin-profit-delete-dialog.component';
import { AdminProfitService } from 'app/entities/admin-profit/admin-profit.service';

describe('Component Tests', () => {
  describe('AdminProfit Management Delete Component', () => {
    let comp: AdminProfitDeleteDialogComponent;
    let fixture: ComponentFixture<AdminProfitDeleteDialogComponent>;
    let service: AdminProfitService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [AdminProfitDeleteDialogComponent]
      })
        .overrideTemplate(AdminProfitDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminProfitDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminProfitService);
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
