export class HabitacionConsulta{
    habitacionId:number;
    fechaIni:Date;
    fechaFin:Date;

    constructor(habitacionId:number, fechaIni:Date, fechaFin:Date){
        this.habitacionId=habitacionId;
        this.fechaIni=fechaIni;
        this.fechaFin=fechaFin;
    }
}