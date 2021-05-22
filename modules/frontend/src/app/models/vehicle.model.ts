import { VehicleType } from "./vehicletype.model";

export class Vehicle {
    id: number | undefined;
    number: string | undefined;
    type: VehicleType | undefined;
    created: string | undefined;
    modified: string | undefined;
}
