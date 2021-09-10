/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MascotaController;
import DAO.Imascota;
import DAO.MascotaDAO;
import Model.Mascota;
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
class PanelMascotas extends JPanel{
    private JLabel jLabelTitulo;
    JTable table = new JTable();
    JScrollPane jsp = new JScrollPane(table);
    MascotaController controller;
    private boolean editable;
    
    public PanelMascotas(){
        Imascota control = new MascotaDAO();
        controller = new MascotaController(control);
        initComponents();
        cargarMascotas();
    }
    
    public void cargarMascotas(){
        table.setModel(controller.consultarMascotas());
        table.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 14));
        table.setFont(new java.awt.Font("Tahoma", 0, 12));
        adjustTextToTable();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        jLabelTitulo = new JLabel("Gestión de mascotas", SwingConstants.CENTER);
        add(jLabelTitulo, BorderLayout.NORTH);
        Font aux = jLabelTitulo.getFont();
        jLabelTitulo.setFont(new Font(aux.getFontName(), aux.getStyle(), 20));
        editable = false;
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 0 || col == 2) {
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
            model.addRow(new Object[]{"ID Mascota", "Nombre", "Usuario mascota"});
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
            if (!oldValue.equals("ID Mascota") && !oldValue.equals("Nombre")) {
                if (!oldValue.equals("Usuario mascota")) {
                    try {
                        int mascotaId = Integer.parseInt(table.getModel().getValueAt(tcl.getRow(), 0).toString());
                        String mascotaNombre = table.getModel().getValueAt(tcl.getRow(), 1).toString();
                        String propUsuario = (table.getModel().getValueAt(tcl.getRow(), 2).toString());
                        
                        Mascota mascota = new Mascota(mascotaId, mascotaNombre, propUsuario);
                        controller.actualizarMascota(mascota);
                    } catch (Exception NumberFormatException) {
                        JOptionPane.showMessageDialog(null, "Ocurrió un error de digitación! ");
                    }
                } else {
                    int mascotaId = Integer.parseInt(table.getModel().getValueAt(tcl.getRow(), 0).toString());
                    String mascotaNombre = table.getModel().getValueAt(tcl.getRow(), 1).toString();
                    String propUsuario = (table.getModel().getValueAt(tcl.getRow(), 2).toString());

                    Mascota mascota = new Mascota(mascotaId, mascotaNombre, propUsuario);
                    controller.agregarMascota(mascota);
                    editable = false;
                }
            }
        }
    };
    
    private void adjustTextToTable() {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    
    private void removeSelectedRows(JTable table) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int column = 0;
        int row = table.getSelectedRow();
        Integer value = Integer.parseInt(model.getValueAt(row, column).toString());
        controller.eliminarMascota(value);
        model.removeRow(row);
    }
    
    
}
