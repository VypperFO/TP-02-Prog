import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewInv {
    JFrame frame;
    JTable tabInv, tabEnt;
    DefaultTableModel modelInv, modelEnt;
    JButton btnFiltre, btnPlusInv, btnMoinsInv, btnPlusEnt, btnMoinsEnt, btnQuit;
    JTextField txfRecherche;
    JMenuBar menuBar;
    JMenu menuTP2, menuFichier;
    JMenuItem miPropos, miQuit, miNouveau, miOuvrir, miFermer, miSave, miSaveTo, miExport;
    JPanel panWest, panEast, panItemsInv, panBtnInv, panBtnEnt, panQuit;
    JFileChooser fc = new JFileChooser();

    Dimension dimTxf = new Dimension(125, 25);
    Dimension dimBtn = new Dimension(125, 25);

    String[] colNamesInv = { "Nom", "Categorie", "Prix", "Date achat", "Description" };
    String[] colNamesEnt = { "Date", "Description" };

    public ViewInv() {
        frame = new JFrame("salope de tp");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // MENU TP-2
        menuTP2 = new JMenu("TP-2");

        miPropos = new JMenuItem("À propos");
        miPropos.addActionListener(e -> miProposAction());
        miQuit = new JMenuItem("Quitter");

        menuTP2.add(miPropos);
        menuTP2.addSeparator();
        menuTP2.add(miQuit);

        // MENU FICHIER
        menuFichier = new JMenu("Fichier");

        miNouveau = new JMenuItem("Nouveau...");
        miNouveau.addActionListener(e -> miNouveauAction());

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

        // MENU BAR
        menuBar = new JMenuBar();
        menuBar.add(menuTP2);
        menuBar.add(menuFichier);

        txfRecherche = new JTextField();
        txfRecherche.setPreferredSize(dimTxf);

        // BUTTONS
        btnFiltre = new JButton("Filtre");

        btnPlusInv = new JButton("+");
        btnPlusInv.setPreferredSize(dimBtn);
        btnPlusInv.addActionListener(e -> btnPlusInvAction());

        btnMoinsInv = new JButton("-");
        btnMoinsInv.setPreferredSize(dimBtn);

        btnPlusEnt = new JButton("+");
        btnPlusEnt.setPreferredSize(dimBtn);
        btnPlusEnt.addActionListener(e -> btnPlusEntAction());

        btnMoinsEnt = new JButton("-");
        btnMoinsEnt.setPreferredSize(dimBtn);

        btnQuit = new JButton("Quitter");
        btnQuit.setPreferredSize(dimBtn);

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

        panWest = new JPanel();
        panWest.setLayout(new BorderLayout());
        panWest.add(tabInv, BorderLayout.CENTER);
        panWest.add(panBtnInv, BorderLayout.SOUTH);

        panBtnEnt = new JPanel();
        panBtnEnt.add(btnPlusEnt);
        panBtnEnt.add(btnMoinsEnt);

        panEast = new JPanel();
        panEast.setLayout(new BorderLayout());
        panEast.add(tabEnt, BorderLayout.CENTER);
        panEast.add(panBtnEnt, BorderLayout.SOUTH);

        panQuit = new JPanel();
        panQuit.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panQuit.add(btnQuit);

        // FRAME
        frame.add(panItemsInv, BorderLayout.NORTH);
        frame.add(panWest, BorderLayout.CENTER);
        frame.add(panEast, BorderLayout.EAST);
        frame.add(panQuit, BorderLayout.SOUTH);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    private void miNouveauAction() {
        fc.setDialogTitle("Nouveau inventaire...");
        int rep = fc.showSaveDialog(frame);
    }

    private void btnPlusEntAction() {
        ViewAjoutEnt ajout = new ViewAjoutEnt();
    }

    private void btnPlusInvAction() {
        ViewAjoutInv ajout = new ViewAjoutInv();
    }

    private void miProposAction() {
        JOptionPane.showMessageDialog(frame,
                "Travail Pratique 2 \n Félix-Olivier Latulippe 2173242 \n Session H2022 \n Dans le cadre du cours 420-C27",
                "À propos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new ViewInv();
    }
}
