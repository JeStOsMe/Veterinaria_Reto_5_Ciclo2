package View;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * @author Jeaniel Osorno
 * Adaptación a este proyecto particular del código igualmente adaptado de mi profe Arles Rodriguez,
 * quien lo obtuvo en primer lugar en el siguiente enlace: 
 * https://tips4java.wordpress.com/2009/06/07/table-cell-listener/
 */
public class TableCellListener implements PropertyChangeListener, Runnable{
    //Clase centrada en "escuchar" cualquier cambio en cualquier celda o fila completa de la GUI.
    //Los 2 métodos principales, propios de Java, escapan de mi comprensión actual.
    private JTable table;
    private Action action;
    
    private int row;
    private int column;
    private Object oldValue;
    private Object newValue;
    
    public TableCellListener(JTable table, Action action){
        this.table = table;
        this.action = action;
        this.table.addPropertyChangeListener(this);
    }
    
    private TableCellListener(JTable table, int row, int column, Object oldValue, Object newValue) {
        this.table = table;
        this.row = row;
        this.column = column;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public JTable getTable() {
        return table;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
    
    

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        //  A cell has started/stopped editing
        if ("tableCellEditor".equals(e.getPropertyName())) {
            if (table.isEditing()) {
                processEditingStarted();
            } else {
                processEditingStopped();
            }
        }
    }
    
    private void processEditingStarted() {
        SwingUtilities.invokeLater(this);
    }
    
    private void processEditingStopped() {
        newValue = table.getModel().getValueAt(row, column);
        if (!newValue.equals(oldValue)) {
            //  Make a copy of the data in case another cell starts editing
            //  while processing this change

            TableCellListener tcl = new TableCellListener(
                    getTable(), getRow(), getColumn(), getOldValue(), getNewValue());

            ActionEvent event = new ActionEvent(
                    tcl,
                    ActionEvent.ACTION_PERFORMED,
                    "");
            action.actionPerformed(event);
        }
    }

    @Override
    public void run() {
        row = table.convertRowIndexToModel(table.getEditingRow());
        column = table.convertColumnIndexToModel(table.getEditingColumn());
        oldValue = table.getModel().getValueAt(row, column);
        newValue = null;
    }
    
    
}
