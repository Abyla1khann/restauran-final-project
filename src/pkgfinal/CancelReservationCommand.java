/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

public class CancelReservationCommand implements ReservationCommand {
    private ReservationReceiver receiver;

    public CancelReservationCommand(ReservationReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cancelReservation();
    }
}