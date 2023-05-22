import { Category } from "./category";

export interface User{
    id: number,
    name: string,
    email: string,
    role: string,
    categories?: Category[];
}
