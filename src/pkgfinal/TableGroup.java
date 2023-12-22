/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.util.ArrayList;
import java.util.List;

public class TableGroup implements TableComponent {
    private List<TableComponent> tables = new ArrayList<>();

    public void addTable(TableComponent table) {
        tables.add(table);
    }

    @Override
    public void displayTableInfo() {
        for (TableComponent table : tables) {
            table.displayTableInfo();
        }
    }
}
