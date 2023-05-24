export interface Reservation{
    id: number,
    customerId: number,
    beautyServiceId: number,
    status: string,
    resDate: Date,
    resHour: number
}