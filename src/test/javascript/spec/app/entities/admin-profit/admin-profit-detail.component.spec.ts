import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaskUniteTestModule } from '../../../test.module';
import { AdminProfitDetailComponent } from 'app/entities/admin-profit/admin-profit-detail.component';
import { AdminProfit } from 'app/shared/model/admin-profit.model';

describe('Component Tests', () => {
  describe('AdminProfit Management Detail Component', () => {
    let comp: AdminProfitDetailComponent;
    let fixture: ComponentFixture<AdminProfitDetailComponent>;
    const route = ({ data: of({ adminProfit: new AdminProfit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaskUniteTestModule],
        declarations: [AdminProfitDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AdminProfitDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminProfitDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.adminProfit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
