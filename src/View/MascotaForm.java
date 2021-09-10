
package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Jeaniel Osorno
 */
public class MascotaForm extends JFrame {
    
    public MascotaForm (){
        initComponents();
    }
    
    private void initComponents(){
        setTitle("GESTIÓN DE MASCOTAS");
        PanelMascotas panelMascotas = new PanelMascotas();
        setContentPane(panelMascotas);
        pack();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new MascotaForm();
    }
    
}
