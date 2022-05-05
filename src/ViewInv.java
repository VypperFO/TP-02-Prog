import java.util.concurrent.Flow;

import javax.swing.*;

public class ViewInv {
    JFrame frame;
    JTable tabInv, tabEnt;
    JButton btnFiltre, btnAjoutInv, btnRemoveInv, btnAjoutEnt, btnRemoveEnt;
    JTextField txfRecherche;
    JMenuBar menuBar;
    JMenu menuTP2, menuFichier;
    JMenuItem miPropos, miQuit, miNouveau, miOuvrir, miFermer, miSave, miSaveTo, miExport;

    public ViewInv() {
        frame = new JFrame("salope de tp");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        // Menu TP 2
        menuTP2 = new JMenu("TP 2");

        miPropos = new JMenuItem("À propos");
        miQuit = new JMenuItem("Quitter");

        menuTP2.add(miPropos);
        menuTP2.addSeparator();
        menuTP2.add(miQuit);

        // Menu Fichier
        menuFichier = new JMenu("Fichier");

        miNouveau = new JMenuItem("Nouveau...");
        miOuvrir = new JMenuItem("Ouvrir...");
        miFermer = new JMenuItem("Fermer");
        miSave = new JMenuItem("Enregistrer");
        miSaveTo = new JMenuItem("Enregistrer sous...");
        miExport = new JMenuItem("Exporter...");

        menuFichier.add(miNouveau);
        menuFichier.add(miOuvrir);
        menuFichier.add(miFermer);
        menuFichier.addSeparator();
        menuFichier.add(miSave);
        menuFichier.add(miSaveTo);
        menuFichier.addSeparator();
        menuFichier.add(miExport);
    }

    public static void main(String[] args) {
        new ViewInv();
    }
}
