package Klassen;

import Interfaces.ICommand;

public class ParkscheinerstellenCommand implements ICommand {

    private String[] ticket;
    private Parkhaus_Fachlogik parkhaus;

    public ParkscheinerstellenCommand(Parkhaus_Fachlogik parkhaus, String[] params) {
        this.parkhaus = parkhaus;
        this.ticket = params;
    }

    @Override
    public void execute() {
        this.parkhaus.addParkschein(ticket);
    }

    @Override
    public void undo() {
        this.parkhaus.removeParkschein();
    }
}
