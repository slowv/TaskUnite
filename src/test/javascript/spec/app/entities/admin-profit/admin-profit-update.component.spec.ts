import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaskUniteTestModule } from '../../../test.module';
import { AdminProfitUpdateComponent } from 'app/entities/admin-profit/admin-profit-update.component';
import { AdminProfitService } from 'app/entities/admin-profit/admin-profit.service';
import { AdminProfit } from 'app/shared/model/admin-profit.model';

describe('Component Tests', () => {
  describe('AdminProfit Management Update Component', () => {
    let comp: AdminProfitUpdateComponent;
    let fixture: ComponentFixture<AdminProfitUpdateComponent>;
    let service: AdminProfitService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [AdminProfitUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AdminProfitUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminProfitUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminProfitService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdminProfit(123);
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
        const entity = new AdminProfit();
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
