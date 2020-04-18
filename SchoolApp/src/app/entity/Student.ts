export interface  Student {
    rollno: number;
    name: string;
    gender: string;
    username: string;
    password: any;
    address: any;
    errorMsg: string;
}

export class Student implements Student{
    rollno: number;
    name: string;
    gender: string;
    username: string;
    password: any;
    address: any;
    errorMsg: string;
}