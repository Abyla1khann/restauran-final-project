/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

public class ReservationInvoker {
    private ReservationCommand command;

    public void setCommand(ReservationCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
