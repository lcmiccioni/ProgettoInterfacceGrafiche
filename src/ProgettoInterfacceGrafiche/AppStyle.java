package ProgettoInterfacceGrafiche;

import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class AppStyle extends JFrame implements ActionListener{

    private JPanel jp = new JPanel();
    private JLabel title = new JLabel("Imposta l'aspetto dell'applicazione");
    private ButtonGroup bg = new ButtonGroup();
    
    public AppStyle() {
        title.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
        jp.add(title);
        for(EnumStyle style : EnumStyle.values()) {
            JRadioButton jrb = new JRadioButton(style.toString());
            jrb.addActionListener(this);
            bg.add(jrb);
            jp.add(jrb);
        }
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        add(jp);
        pack();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void setLookAndFeel(EnumStyle style) {
        try {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.Synthetica" + style + "LookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and feel non trovato!");
        }
    }
    
    public static EnumStyle getCurrentLookAndFeel() {
        EnumStyle es = null;
        try (BufferedReader in = new BufferedReader(new FileReader("style" + File.separator + "style.txt"))){
            es = EnumStyle.valueOf(in.readLine());
            in.close();
        }catch(Exception e) {
            System.out.println("File non trovato!");
        }
        return es;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("style" + File.separator + "style.txt"))){
            out.write(((JRadioButton)e.getSource()).getText());
            out.close();
        }catch(Exception ex) {
            System.out.println("File non trovato!");
        }
    }
}

