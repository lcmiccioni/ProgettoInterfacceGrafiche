package ProgettoInterfacceGrafiche;

import java.io.*;
import java.util.*;

public class Utenza {
    
    private static List<Utente> utenti;
    private static final String folderName = "utenti";
    private static Utente currentUtente;
    
    public Utenza() {
        utenti = new ArrayList();
        fillList();
    }
    
    private void fillList() {
        File file = new File(folderName);
        if(!file.exists()) {
            file.mkdir();
        }else {
            for(File f : file.listFiles()) {
                try(BufferedReader in = new BufferedReader(new FileReader(f))) {
                    Utente newUser = new Utente(in.readLine(), in.readLine(), in.readLine(), in.readLine(), Float.valueOf(in.readLine()));
                    String nome;
                    while((nome = in.readLine()) != null) {
                        newUser.addToCart(Negozio.getProdotto(nome));
                    }
                    utenti.add(newUser);
                    in.close();
                }catch(Exception e) {}
            }
        }
    }
    
    public static void setCurrentUtente(Utente utente) {
        currentUtente = utente;
    }
    
    public static Utente getCurrentUtente() {
        return currentUtente;
    }
    
    public static boolean newUtente(Utente utente) {
        File output = new File(folderName + File.separator + "user" + utenti.size() + ".txt");
        
        if(!exist(utente.getEmail())) {
            try(BufferedWriter out = new BufferedWriter(new FileWriter(output))) {
                out.write(utente.getNome() + System.getProperty("line.separator")
                        + utente.getCognome() + System.getProperty("line.separator")
                        + utente.getEmail() + System.getProperty("line.separator")
                        + utente.getPassword() + System.getProperty("line.separator")
                        + utente.getSaldo());
                out.close();
            }catch(Exception e) {
                return false;
            }
            utenti.add(utente);
            return true;
        }
        
        return false;
    }
    
    public static void updateUtente() {
        if(currentUtente != null) {
            File output = new File(folderName + File.separator + "user" + utenti.indexOf(currentUtente) + ".txt");

            try(BufferedWriter out = new BufferedWriter(new FileWriter(output))) {
                out.write(currentUtente.getNome() + System.getProperty("line.separator")
                        + currentUtente.getCognome() + System.getProperty("line.separator")
                        + currentUtente.getEmail() + System.getProperty("line.separator")
                        + currentUtente.getPassword() + System.getProperty("line.separator")
                        + currentUtente.getSaldo() + System.getProperty("line.separator"));
                for(Prodotto p : currentUtente.getCart()) {
                    out.write(p.getNome() + System.getProperty("line.separator"));
                }
                out.close();
            }catch(Exception e) {}
            utenti.add(currentUtente);
        }
    }
    
    public static Utente getUtente(String email) {
        for(Utente utente : utenti) {
            if(utente.getEmail().equals(email)) {
                return utente;
            }
        }
        return null;
    }
    
    public static boolean exist(String email) {
        for(Utente utente : utenti) {
            if(utente.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean getUserData(String email, String password) {
        for(Utente utente : utenti) {
            if(utente.getEmail().equals(email) && utente.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
}
