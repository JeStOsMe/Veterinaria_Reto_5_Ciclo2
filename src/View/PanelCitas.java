/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CitaController;
import DAO.Icita;
import DAO.CitaDAO;
import Model.Cita;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Jeaniel Osorno
 */
public class PanelCitas extends JPanel{
    
    private JLabel jLabelTitulo;
    JTable table = new JTable();
    JScrollPane jsp = new JScrollPane(table);
    CitaController controller;
    private boolean editable;
    
    public PanelCitas(){
        Icita control = new CitaDAO();
        controller = new CitaController(control);
        initComponents();
        cargarCitas();
    }
    
    public void cargarCitas(){
        table.setModel(controller.consultarCitas());
        table.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 14));
        table.setFont(new java.awt.Font("Tahoma", 0, 12));
        adjustTextToTable();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        jLabelTitulo = new JLabel("Gestión de citas", SwingConstants.CENTER);
        add(jLabelTitulo, BorderLayout.NORTH);
        Font aux = jLabelTitulo.getFont();
        jLabelTitulo.setFont(new Font(aux.getFontName(), aux.getStyle(), 20));
        editable = false;
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 0 || col == 3) {
                    return editable; //To change body of generated methods, choose Tools | Templates.
                }
                return true;
            }
        };
        jsp = new JScrollPane(table);
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete selected row...");
        deleteItem.addActionListener((ActionEvent e) -> {
            removeSelectedRows(table);
            //JOptionPane.showMessageDialog(null, "Right-click performed on table and choose DELETE");
        });
        popupMenu.add(deleteItem);

        JMenuItem addItem = new JMenuItem("Add...");
        addItem.addActionListener((ActionEvent e) -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            editable = true;
            model.addRow(new Object[]{"ID Cita", "Fecha", "Descripción", "ID Mascota"});
        });
        popupMenu.add(addItem);

        table.setComponentPopupMenu(popupMenu);
        TableCellListener tcl = new TableCellListener(table, action);
        add(jsp, BorderLayout.AFTER_LAST_LINE);
    }
    
    Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TableCellListener tcl = (TableCellListener) e.getSource();
            System.out.println("Row   : " + tcl.getRow());
            System.out.println("Column: " + tcl.getColumn());
            System.out.println("Old   : " + tcl.getOldValue());
            System.out.println("New   : " + tcl.getNewValue());
            String oldValue = tcl.getOldValue().toString();
            if (!oldValue.equals("ID Cita") && !oldValue.equals("Fecha") && !oldValue.equals("Descripción")) {
                if (!oldValue.equals("ID Mascota")) {
                    try {
                        int citaId = Integer.parseInt(table.getModel().getValueAt(tcl.getRow(), 0).toString());
                        String citaFecha = table.getModel().getValueAt(tcl.getRow(), 1).toString();
                        String citaDescripcion = (table.getModel().getValueAt(tcl.getRow(), 2).toString());
                        int mascotaIdFK = Integer.parseInt(table.getModel().getValueAt(tcl.getRow(), 3).toString());
                        
                        Cita cita = new Cita(citaId, citaFecha, citaDescripcion, mascotaIdFK);
                        controller.actualizarCita(cita);
                    } catch (Exception NumberFormatException) {
                        JOptionPane.showMessageDialog(null, "Error en la digitación! ");
                    }
                } else {
                    Integer citaId = Integer.parseInt(table.getModel().getValueAt(tcl.getRow(), 0).toString());
                    String citaFecha = table.getModel().getValueAt(tcl.getRow(), 1).toString();
                    String citaDescripcion = (table.getModel().getValueAt(tcl.getRow(), 2).toString());
                    Integer mascotaIdFK = Integer.parseInt(table.getModel().getValueAt(tcl.getRow(), 3).toString());
                    
                    Cita cita = new Cita(citaId, citaFecha, citaDescripcion, mascotaIdFK);
                    controller.agregarCita(cita);
                    editable = false;
                }
            }
        }
    };
    
    private void adjustTextToTable() {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 100; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 800) {
                width = 800;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    
    private void removeSelectedRows(JTable table) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int column = 0;
        int row = table.getSelectedRow();
        Integer value = Integer.parseInt(model.getValueAt(row, column).toString());
        controller.eliminarCita(value);
        model.removeRow(row);
    }
    
}
