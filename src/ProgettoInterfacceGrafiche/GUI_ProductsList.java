package ProgettoInterfacceGrafiche;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

class GUI_ProductsList extends JPanel implements ActionListener{
    
    private GUI_Overview frame;
    protected JScrollPane jsp;
    
    public GUI_ProductsList() {
        setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 91 / 100));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jsp = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    public void setPage(List<Prodotto> prodotti) {
        removeAll();
        for(Prodotto p : prodotti) {
            JButton prodotto = new JButton(p.getNome());
            prodotto.setActionCommand("showPage");
            prodotto.setAlignmentX(Component.CENTER_ALIGNMENT);
            prodotto.addActionListener(this);
            prodotto.setCursor(new Cursor(12));
            add(prodotto);
        }
    }
    
    public void setFrame(GUI_Overview frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "showPage":
                frame.BuildProductPage(Negozio.getProdotto(((JButton)e.getSource()).getText()));
                break;
        }
    }
    
}
