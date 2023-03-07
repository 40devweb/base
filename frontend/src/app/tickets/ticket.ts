export class Ticket {
    id: number = 0;
    creationDttm: string = '';
    text: string = '';

    constructor(id: number, creationDttm: string, text: string) {
        this.id = id;
        this.creationDttm = creationDttm;
        this.text = text;
    }
}

