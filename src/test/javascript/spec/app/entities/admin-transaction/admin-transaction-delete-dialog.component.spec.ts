import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TaskUniteTestModule } from '../../../test.module';
import { AdminTransactionDeleteDialogComponent } from 'app/entities/admin-transaction/admin-transaction-delete-dialog.component';
import { AdminTransactionService } from 'app/entities/admin-transaction/admin-transaction.service';

describe('Component Tests', () => {
  describe('AdminTransaction Management Delete Component', () => {
    let comp: AdminTransactionDeleteDialogComponent;
    let fixture: ComponentFixture<AdminTransactionDeleteDialogComponent>;
    let service: AdminTransactionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [AdminTransactionDeleteDialogComponent]
      })
        .overrideTemplate(AdminTransactionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminTransactionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminTransactionService);
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
