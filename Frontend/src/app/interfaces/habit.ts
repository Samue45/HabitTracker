export interface habit{
    id?: number;
    name: string;
    description: string;
    category: string;
    priority:string;
    daysPerWeek:number;
    daysOfWeek: string[];
    completed: boolean;

}