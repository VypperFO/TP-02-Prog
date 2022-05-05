import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewInv {
    JFrame frame;
    JTable tabInv, tabEnt;
    DefaultTableModel modelInv, modelEnt;
    JButton btnFiltre, btnPlusInv, btnMoinsInv, btnPlusEnt, btnMoinsEnt;
    JTextField txfRecherche;
    JMenuBar menuBar;
    JMenu menuTP2, menuFichier;
    JMenuItem miPropos, miQuit, miNouveau, miOuvrir, miFermer, miSave, miSaveTo, miExport;
    JPanel panWest, panEast, panItemsInv, panBtnInv, panBtnEnt;

    Dimension dimTxf = new Dimension(125, 25);
    Dimension dimBtn = new Dimension(125, 25);

    String[] colNamesInv = { "Nom", "Categorie", "Prix", "Date achat", "Description" };
    String[] colNamesEnt = { "Date", "Description" };

    String[][] data = {
    {"1", "pdA", "Le produit A", "pdA", "Le produit A"},
    {"2", "pdB", "Le produit B", "pdA", "Le produit A"},
    {"3", "pdC", "Le produit C", "pdA", "Le produit A"}
};
    public ViewInv() {
        frame = new JFrame("salope de tp");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);


        // Menu TP 2
        menuTP2 = new JMenu("TP-2");

        miPropos = new JMenuItem("À propos");
        miPropos.addActionListener(e -> miProposAction());
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

        // Menu bar
        menuBar = new JMenuBar();
        menuBar.add(menuTP2);
        menuBar.add(menuFichier);

        txfRecherche = new JTextField();
        txfRecherche.setPreferredSize(dimTxf);
        
        // BUTTONS
        btnFiltre = new JButton("Filtre");

        btnFiltre.setMaximumSize(dimBtn);
        btnPlusInv = new JButton("+");
        btnPlusInv.setPreferredSize(dimBtn);
        btnMoinsInv = new JButton("-");
        btnMoinsInv.setPreferredSize(dimBtn);

        btnPlusEnt = new JButton("+");
        btnPlusEnt.setPreferredSize(dimBtn);
        btnMoinsEnt = new JButton("-");
        btnMoinsEnt.setPreferredSize(dimBtn);
        
        // TABLES
        modelInv = new DefaultTableModel();
        tabInv = new JTable(modelInv);
        JScrollPane scrollPaneInv = new JScrollPane(tabInv);
        scrollPaneInv.setPreferredSize(new Dimension(100, 100));
        
        modelEnt = new DefaultTableModel();
        tabEnt = new JTable(modelEnt);
        JScrollPane scrollPaneEnt = new JScrollPane(tabEnt);
        scrollPaneEnt.setPreferredSize(new Dimension(100, 100));


        // PANEL
        panItemsInv = new JPanel();
        panItemsInv.setLayout(new FlowLayout(FlowLayout.LEFT));
        panItemsInv.add(txfRecherche);
        panItemsInv.add(btnFiltre);

        panBtnInv = new JPanel();
        panBtnInv.setLayout(new FlowLayout(FlowLayout.LEFT));
        panBtnInv.add(btnPlusInv);
        panBtnInv.add(btnMoinsInv);

        panEast = new JPanel();
        panEast.setLayout(new FlowLayout());
        panEast.add(tabEnt);
        panEast.add(btnPlusEnt);
        panEast.add(btnMoinsEnt);

        frame.add(panItemsInv, BorderLayout.NORTH);
        frame.add(tabInv, BorderLayout.CENTER);
        frame.add(panBtnInv, BorderLayout.SOUTH);
        frame.add(panEast, BorderLayout.EAST);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    private void miProposAction() {
        JOptionPane.showMessageDialog(frame, "Travail Pratique 2 \n Félix-Olivier Latulippe 2173242 \n Session H2022 \n Dans le cadre du cours 420-C27", "À propos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new ViewInv();
    }
}
