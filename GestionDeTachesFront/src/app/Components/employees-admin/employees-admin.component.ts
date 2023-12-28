import { Component, OnInit } from '@angular/core';
import {FormControlerService} from "../../Services/form-controles.service";
import {FormControl, FormGroup} from "@angular/forms";
import {MemberService} from "../../Services/member.service";
import {User} from "../../Models/user";

@Component({
  selector: 'app-employees-admin',
  templateUrl: './employees-admin.component.html',
  styleUrls: ['./employees-admin.component.css']
})
export class EmployeesAdminComponent implements OnInit {
listMembers:User []=[];
member:User=new User();

  constructor(public formService:FormControlerService,
              private memberService :MemberService,
              ) { }

  ngOnInit(): void {
    this.memberService.getAllMembers().subscribe(
      data=>{
        console.log('data : ',data);
        this.listMembers=data as User[];
        console.log('list members : ',this.listMembers);
      });

  }

  onClear() {
    this.formService.formGroupAddEmp.reset();
  }
onSubmit() {
    if(this.formService.formGroupAddEmp.valid){
      console.log('form submitted');
      this.member.email=this.formService.formGroupAddEmp.value.emailEmp;
      //this.member.
    }else{
      console.log('invalid form');
      this.validateAllFormFields(this.formService.formGroupAddEmp);
    }
}
  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    } );
  }
}
