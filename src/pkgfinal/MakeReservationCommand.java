/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

public class MakeReservationCommand implements ReservationCommand {
    private ReservationReceiver receiver;

    public MakeReservationCommand(ReservationReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.makeReservation();
    }
}
