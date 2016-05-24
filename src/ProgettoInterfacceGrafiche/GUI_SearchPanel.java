package ProgettoInterfacceGrafiche;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

class GUI_SearchPanel extends JPanel implements ActionListener{
    
    private GUI_Overview frame;
    
    private JLabel logo = new JLabel();
    private JComboBox categories = new JComboBox();
    private JTextField searchArea = new JTextField(30);
    private JButton search = new JButton("");
    
    public GUI_SearchPanel() {
        ImageIcon logoIcon = new ImageIcon("icone" + File.separator + "Logo.png");
        Image imglogo = logoIcon.getImage().getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 3 / 100, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 3 / 100,  java.awt.Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(imglogo);
        logo.setIcon(logoIcon);
        createCategories();
        searchArea.setActionCommand("Search");
        searchArea.addActionListener(this);
        ImageIcon searchIcon = new ImageIcon("icone" + File.separator + "Search.png");
        Image imgSearch = searchIcon.getImage().getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 100, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 100,  java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(imgSearch);
        search.setIcon(searchIcon);
        search.setActionCommand("Search");
        search.addActionListener(this);
        search.setCursor(new Cursor(12));
        add(logo);
        add(categories);
        add(searchArea);
        add(search);
        setBorder(LineBorder.createBlackLineBorder());
    }
    
    public void setFrame(GUI_Overview frame) {
        this.frame = frame;
    }
    
    private void createCategories() {
        for(String categoria : Negozio.getCategories()) {
            categories.addItem(categoria);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Search":
                if(!Negozio.searchProducts(searchArea.getText().toLowerCase(), categories.getSelectedItem().toString().toLowerCase()).isEmpty()) {
                    frame.BuildProductsList(Negozio.searchProducts(searchArea.getText().toLowerCase(), categories.getSelectedItem().toString().toLowerCase()));
                    searchArea.setText("");
                }else {
                    frame.BuildProductsList(new ArrayList());
                    JOptionPane.showMessageDialog(null, "Nessuna corrispondenza!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                frame.repaint();
                frame.revalidate();
                break;
        }
    }
    
}
