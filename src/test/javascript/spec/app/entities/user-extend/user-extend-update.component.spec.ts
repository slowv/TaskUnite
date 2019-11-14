import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaskUniteTestModule } from '../../../test.module';
import { UserExtendUpdateComponent } from 'app/entities/user-extend/user-extend-update.component';
import { UserExtendService } from 'app/entities/user-extend/user-extend.service';
import { UserExtend } from 'app/shared/model/user-extend.model';

describe('Component Tests', () => {
  describe('UserExtend Management Update Component', () => {
    let comp: UserExtendUpdateComponent;
    let fixture: ComponentFixture<UserExtendUpdateComponent>;
    let service: UserExtendService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [UserExtendUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UserExtendUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserExtendUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserExtendService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserExtend(123);
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
        const entity = new UserExtend();
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
