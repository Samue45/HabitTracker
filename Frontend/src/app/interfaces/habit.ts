export interface Habit{
    id?: number;
    name: string;
    description: string;
    type: string;
    level_priority:string;
    /*daysPerWeek:number;
    daysOfWeek: string[];*/
    state: boolean;
    nameDay: string;

}