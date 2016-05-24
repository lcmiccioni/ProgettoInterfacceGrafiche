package ProgettoInterfacceGrafiche;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class GUI_MenuBar extends JMenuBar implements ActionListener{

    private GUI_Overview frame;
    
    private JMenu jmAccesso = new JMenu("Accedi");
    private JMenuItem jmiAccedi = new JMenuItem("Accedi");
    private JMenuItem jmiRegistrati = new JMenuItem("Registrati");
    
    private static JMenu jmAccount = new JMenu("Account");
    private JMenuItem jmiAccount = new JMenuItem("Il mio Account");
    private JMenuItem jmiCart = new JMenuItem("Carrello");
    private JMenuItem jmiStyle = new JMenuItem("Stile");
    
    private JMenu jmEsci = new JMenu("Esci");
    private JMenuItem jmiEsci = new JMenuItem("Esci");
    
    public GUI_MenuBar() {
        jmAccesso.add(jmiAccedi);
        jmiAccedi.setActionCommand("LogIn");
        jmiAccedi.addActionListener(this);
        jmAccesso.add(jmiRegistrati);
        jmiRegistrati.setActionCommand("Sign up");
        jmiRegistrati.addActionListener(this);
        add(jmAccesso);
        jmAccount.add(jmiAccount);
        jmiAccount.setActionCommand("Account");
        jmiAccount.addActionListener(this);
        jmAccount.add(jmiCart);
        ImageIcon icon = new ImageIcon("icone" + File.separator + "Cart.png");
        Image newimg = icon.getImage().getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 100, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 100,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        jmiCart.setIcon(icon);
        jmiCart.setActionCommand("Cart");
        jmiCart.addActionListener(this);
        jmAccount.add(jmiStyle);
        jmiStyle.setActionCommand("Style");
        jmiStyle.addActionListener(this);
        jmAccount.setVisible(false);
        add(jmAccount);
        jmEsci.add(jmiEsci);
        jmiEsci.setActionCommand("Esci");
        jmiEsci.addActionListener(this);
        add(Box.createHorizontalGlue());
        add(jmEsci);
    }
    
    public void setFrame(GUI_Overview frame) {
        this.frame = frame;
    }
    
    public void isLogged() {
        jmAccount.setVisible(true);
        jmiAccedi.setText("Cambia utente");
        jmiRegistrati.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Esci":
                switch(JOptionPane.showConfirmDialog(null, "Vuoi uscire veramente?", "Esci", JOptionPane.YES_NO_OPTION)) {
                    case 0:
                        Utenza.updateUtente();
                        System.exit(0);
                        break;
                }
                break;
            case "LogIn":
                Utenza.updateUtente();
                new LogIn(frame);
                break;
            case "Sign up":
                new SignUp();
                break;
            case "Account":
                frame.BuildInfoPage();
                break;
            case "Cart":
                frame.BuildCartPage();
                break;
            case "Style":
                new AppStyle();
                break;
        }
    }
    
}
