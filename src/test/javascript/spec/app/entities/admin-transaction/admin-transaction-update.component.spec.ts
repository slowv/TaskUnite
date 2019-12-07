import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaskUniteTestModule } from '../../../test.module';
import { AdminTransactionUpdateComponent } from 'app/entities/admin-transaction/admin-transaction-update.component';
import { AdminTransactionService } from 'app/entities/admin-transaction/admin-transaction.service';
import { AdminTransaction } from 'app/shared/model/admin-transaction.model';

describe('Component Tests', () => {
  describe('AdminTransaction Management Update Component', () => {
    let comp: AdminTransactionUpdateComponent;
    let fixture: ComponentFixture<AdminTransactionUpdateComponent>;
    let service: AdminTransactionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [AdminTransactionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AdminTransactionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminTransactionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminTransactionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdminTransaction(123);
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
        const entity = new AdminTransaction();
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
