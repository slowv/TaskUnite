import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaskUniteTestModule } from '../../../test.module';
import { AdminTransactionDetailComponent } from 'app/entities/admin-transaction/admin-transaction-detail.component';
import { AdminTransaction } from 'app/shared/model/admin-transaction.model';

describe('Component Tests', () => {
  describe('AdminTransaction Management Detail Component', () => {
    let comp: AdminTransactionDetailComponent;
    let fixture: ComponentFixture<AdminTransactionDetailComponent>;
    const route = ({ data: of({ adminTransaction: new AdminTransaction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [AdminTransactionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AdminTransactionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminTransactionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.adminTransaction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
