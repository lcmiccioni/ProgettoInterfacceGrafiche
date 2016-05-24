package ProgettoInterfacceGrafiche;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI_Cart extends JPanel implements ActionListener{
    
    private JLabel title;
    protected JScrollPane jsp;
    
    public GUI_Cart() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jsp = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        title = new JLabel("Carrello");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        addElements();
    }
    
    public void addElements() {
        removeAll();
        add(title);
        Utente user;
        if((user = Utenza.getCurrentUtente()) != null) {
            for(Prodotto p : user.getCart()) {
                JLabel prodotto = new JLabel(p.getNome() + ", " + p.getPrezzo() + "€");
                prodotto.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(prodotto);
                JButton remove = new JButton("Rimuovi");
                remove.setName(p.getNome());
                remove.setAlignmentX(Component.CENTER_ALIGNMENT);
                remove.setCursor(new Cursor(12));
                remove.setActionCommand("Remove");
                remove.addActionListener(this);
                add(remove);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Remove":
                Utenza.getCurrentUtente().removeFromCart(Negozio.getProdotto(((JButton)e.getSource()).getName()));
                Utenza.getCurrentUtente().addSaldo(Negozio.getProdotto(((JButton)e.getSource()).getName()).getPrezzo());
                addElements();
                repaint();
                revalidate();
                break;
        }
    }
    
}
