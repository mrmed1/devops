import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTaskParProjetComponent } from './list-task-par-projet.component';

describe('ListTaskParProjetComponent', () => {
  let component: ListTaskParProjetComponent;
  let fixture: ComponentFixture<ListTaskParProjetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListTaskParProjetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListTaskParProjetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
