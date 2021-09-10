package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Jeaniel Osorno
 */
public class PropietarioForm extends JFrame {
    //Crea un JFrame, donde todas las operaciones CRUD se realizarán de forma gráfica.
    public PropietarioForm (){
        initComponents();
    }
    //Inicialización de los componentes básicos y del contenido principal del JFrame
    private void initComponents(){
        setTitle("GESTIÓN DE USUARIOS");
        PanelPropietarios panelPropietarios = new PanelPropietarios();
        setContentPane(panelPropietarios);
        pack(); 
        //Para centrar la GUI en el monitor, sea cual sea.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new PropietarioForm();
    }
    
}
