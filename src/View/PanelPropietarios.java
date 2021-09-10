package View;

import Controller.PropietarioController;
import DAO.Ipropietario;
import DAO.PropietarioDAO;
import Model.Propietario;
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
 * @author Jeaniel Osorno
 */
class PanelPropietarios extends JPanel{
    //Clase centrada en crear la tabla en la GUI y llamar las operaciones CRUD.
    private JLabel jLabelTitulo;
    JTable table = new JTable();
    JScrollPane jsp = new JScrollPane(table);
    PropietarioController controller;
    private boolean editable;
    
    //El constructor nos prepara la tabla con sus respectivos campos, 
    //Y luego se nutre de la información en la base de datos.
    public PanelPropietarios(){
        Ipropietario control = new PropietarioDAO();
        controller = new PropietarioController(control);
        initComponents();
        cargarPropietarios();
    }
    //Llama a todos los propietarios, los agrega fila por fila, con una fuente 
    //específica y modificable, y luego ajusta el texto a la tabla.
    public void cargarPropietarios(){
        table.setModel(controller.consultarPropietarios());
        table.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 14));
        table.setFont(new java.awt.Font("Tahoma", 0, 12));
        adjustTextToTable();
    }
    /*Inicia los componentes de la tabla, como la tabla misma (incluyendo una restricción para evitar 
    modificaciones indeseables en la base de datos), la barra de scroll lateral y el menú PopUp (que 
    se desplegará al presionar clic derecho), el cual incluye las opciones de agregar o eliminar un 
    registroa la base de datos.
    */
    private void initComponents() {
        setLayout(new BorderLayout());
        jLabelTitulo = new JLabel("Gestión de usuarios", SwingConstants.CENTER);
        add(jLabelTitulo, BorderLayout.NORTH);
        Font aux = jLabelTitulo.getFont();
        jLabelTitulo.setFont(new Font(aux.getFontName(), aux.getStyle(), 20));
        editable = false;
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 0) {
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
            model.addRow(new Object[]{"Usuario", "Apellido", "Nombre", "Teléfono"});
        });
        popupMenu.add(addItem);

        table.setComponentPopupMenu(popupMenu);
        TableCellListener tcl = new TableCellListener(table, action);
        add(jsp, BorderLayout.AFTER_LAST_LINE);
    }
    /*Método centrado en detectar cualquier acción (cambios en las celdas) e imprime
    estos a nivel desarrollador. Si ocurriece un error (por ejemplo, letras envés de 
    números), enviaría un mensaje a nivel usuario.*/
    Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TableCellListener tcl = (TableCellListener) e.getSource();
            System.out.println("Row   : " + tcl.getRow());
            System.out.println("Column: " + tcl.getColumn());
            System.out.println("Old   : " + tcl.getOldValue());
            System.out.println("New   : " + tcl.getNewValue());
            String oldValue = tcl.getOldValue().toString();
            if (!oldValue.equals("Usuario") && !oldValue.equals("Apellido") && !oldValue.equals("Nombre")) {
                if (!oldValue.equals("Teléfono")) {
                    try {
                        String propUsuario = (table.getModel().getValueAt(tcl.getRow(), 0).toString());
                        String propApellido = table.getModel().getValueAt(tcl.getRow(), 1).toString();
                        String propNombre = (table.getModel().getValueAt(tcl.getRow(), 2).toString());
                        String propTelefono = (table.getModel().getValueAt(tcl.getRow(), 3).toString());
                        //String propTelefono = String.valueOf(aux);
                        Propietario propietario = new Propietario(propUsuario, propApellido, propNombre, propTelefono);
                        controller.actualizarPropietario(propietario);
                    } catch (Exception NumberFormatException) {
                        JOptionPane.showMessageDialog(null, "Error en la digitación! ");
                    }
                } else {
                    String propUsuario = (table.getModel().getValueAt(tcl.getRow(), 0).toString());
                    String propApellido = table.getModel().getValueAt(tcl.getRow(), 1).toString();
                    String propNombre = (table.getModel().getValueAt(tcl.getRow(), 2).toString());
                    String propTelefono = (table.getModel().getValueAt(tcl.getRow(), 3).toString());
                    
                    Propietario propietario = new Propietario(propUsuario, propApellido, propNombre, propTelefono);
                    controller.agregarPropietario(propietario);
                    editable = false;
                }
            }
        }
    };
    //Ajusta el texto a la tabla, estableciendo el ancho mínimo y máximo que ocupará el texto.
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
    /*Si se presiona clic derecho en una fila, y luego se elige la opción eliminar,
    este método lo detectará, tomará la clave primaria de esa fila y llamará al
    método CRUD eliminar, usando como parámetro dicha clave primaria*/
    private void removeSelectedRows(JTable table) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int column = 0;
        int row = table.getSelectedRow();
        String value = model.getValueAt(row, column).toString();
        controller.eliminarPropietario(value);
        model.removeRow(row);
    }
    
    
}
