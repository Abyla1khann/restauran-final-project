/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

/**
 *
 *
 */
public class IndividualTable implements TableComponent {
    private int tableNumber;

    public IndividualTable(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public void displayTableInfo() {
        System.out.println("Individual Table: " + tableNumber);
    }
}
