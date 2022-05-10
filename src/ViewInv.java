import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class ViewInv {
    JFrame frame;
    static JTable tabInv;
    JTable tabEnt;
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

    public boolean isSave = false;
    public boolean isNouveau = false;
    public String title = "";

    public static ArrayList<Inventaire> listInventaire;

    public ViewInv() throws IOException {
        frame = new JFrame(title + "Félix-Olivier 2173242");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // MENU TP-2
        menuTP2 = new JMenu("TP-2");

        miPropos = new JMenuItem("À propos");
        miPropos.addActionListener(e -> miProposAction());

        miQuit = new JMenuItem("Quitter");
        miQuit.addActionListener(e -> miQuitAction());

        menuTP2.add(miPropos);
        menuTP2.addSeparator();
        menuTP2.add(miQuit);

        // MENU FICHIER
        menuFichier = new JMenu("Fichier");

        miNouveau = new JMenuItem("Nouveau...");
        miNouveau.addActionListener(e -> miNouveauAction());

        miOuvrir = new JMenuItem("Ouvrir...");
        miOuvrir.addActionListener(e -> miOuvrirAction());

        miFermer = new JMenuItem("Fermer");
        miFermer.addActionListener(e -> miFermerAction());

        miSave = new JMenuItem("Enregistrer");
        miSave.addActionListener(e -> miSaveAction());

        miSaveTo = new JMenuItem("Enregistrer sous...");
        miSaveTo.addActionListener(e -> miSaveToAction());

        miExport = new JMenuItem("Exporter...");
        miExport.addActionListener(e -> miExportAction());

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

        // TABLES
        listInventaire = listInventaire();

        modelInv = new DefaultTableModel(colNamesInv, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabInv = new JTable(modelInv);
        tabInv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {

                    new ViewModifInv();
                    update();
                }
            }
        });
        tabInv.setAutoCreateRowSorter(true);
        tabInv.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelInv);
        tabInv.setRowSorter(sorter);

        modelEnt = new DefaultTableModel(colNamesEnt, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabEnt = new JTable(modelEnt);
        tabEnt.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // BUTTONS
        btnFiltre = new JButton("Filtre");
        btnFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = txfRecherche.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                        tabInv.getSelectionModel().clearSelection();
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(frame, "Une Erreur est Survenue");
                    }
                }
            }
        });

        btnPlusInv = new JButton("+");
        btnPlusInv.setPreferredSize(dimBtn);
        btnPlusInv.addActionListener(e -> btnPlusInvAction());

        btnMoinsInv = new JButton("-");
        btnMoinsInv.setPreferredSize(dimBtn);
        btnMoinsInv.addActionListener(e -> btnMoinsInvAction());

        btnPlusEnt = new JButton("+");
        btnPlusEnt.setPreferredSize(dimBtn);
        btnPlusEnt.addActionListener(e -> btnPlusEntAction());

        btnMoinsEnt = new JButton("-");
        btnMoinsEnt.setPreferredSize(dimBtn);
        btnMoinsEnt.addActionListener(e -> btnMoinsEntAction());

        btnQuit = new JButton("Quitter");
        btnQuit.setPreferredSize(dimBtn);
        btnQuit.addActionListener(e -> btnQuitAction());

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (((KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, java.awt.event.InputEvent.ALT_DOWN_MASK)) != null)
                        && e.getKeyCode() == KeyEvent.VK_F4) {
                    miQuitAction();
                }
            }
        });

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
        panWest.add(new JScrollPane(tabInv), BorderLayout.CENTER);
        panWest.add(panBtnInv, BorderLayout.SOUTH);

        panBtnEnt = new JPanel();
        panBtnEnt.add(btnPlusEnt);
        panBtnEnt.add(btnMoinsEnt);

        panEast = new JPanel();
        panEast.setLayout(new BorderLayout());
        panEast.add(new JScrollPane(tabEnt), BorderLayout.CENTER);
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

    /*
     * @@@@@@@@@@@@@
     *
     * @@@ Menu @@@
     *
     * @@@@@@@@@@@@@@
     */

    private void miProposAction() {
        JOptionPane.showMessageDialog(frame,
                "Travail Pratique 2 \n Félix-Olivier Latulippe 2173242 \n Session H2022 \n Dans le cadre du cours 420-C27",
                "À propos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void miQuitAction() {
        int reponse = JOptionPane.showConfirmDialog(frame, "Voulez-vous quitter?", "Quitter",
                JOptionPane.YES_NO_OPTION); // Réponse de l'utilisateur

        if (reponse == JOptionPane.YES_OPTION) {
            if (!isSave) {
                int reponseSave = JOptionPane.showConfirmDialog(frame, "Voulez-vous sauvegarder?", "Quitter",
                        JOptionPane.YES_NO_OPTION); // Réponse de l'utilisateur

                if (reponseSave == JOptionPane.YES_OPTION) {
                    try {
                        miSaveAction();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.exit(0);
                }
            }

            System.exit(0);
        }
    }

    private void miNouveauAction() {
        fc.setDialogTitle("Nouveau inventaire...");
        int rep = fc.showSaveDialog(frame);
        isNouveau = true;
    }

    private void miOuvrirAction() {
        fc.setDialogTitle("Ouverture inventaire");

        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.dat", "dat");
        fc.setFileFilter(fileFilter);

        int rep = fc.showOpenDialog(frame);
        if (rep == JFileChooser.APPROVE_OPTION) {
            File fichier = fc.getSelectedFile();

            String filePath = fichier.getPath();
            try {
                if (!isInventaireOuvert()) {
                    readFileObject(filePath);
                    for (Inventaire object : listInventaire) {
                        modelInv.addRow(
                                new Object[] { object.getNom(), object.getCategorie(), object.getPrix(),
                                        object.getDateAchat(),
                                        object.getDescription() });
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Inventaire déjà ouverte");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error");
                System.out.println(e.getMessage());
            }
        }
    }

    private void miFermerAction() {
        modelInv.setRowCount(0);
        listInventaire.clear();
        isNouveau = false;
        isSave = false;
    }

    private void miSaveAction() {
        if (isInventaireOuvert()) {
            File fichier = fc.getSelectedFile();
            String filePath = fichier.getPath();

            try {
                writeFileObject(filePath);
                isSave = true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Aucun inventaire ouvert");
        }
    }

    private void miSaveToAction() {
        fc.setDialogTitle("Enregistrement inventaire");

        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.dat", "dat");
        fc.setFileFilter(fileFilter);

        if (isInventaireOuvert()) {
            int rep = fc.showSaveDialog(frame);

            if (rep == JFileChooser.APPROVE_OPTION) {
                File fichier = fc.getSelectedFile();

                String filePath = fichier.getPath();
                try {
                    writeFileObject(filePath);
                    isSave = true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Error");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Aucun inventaire ouvert");
        }
    }

    private void miExportAction() {
        fc.setDialogTitle("Exportation fichier texte");

        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.txt", "txt");
        fc.setFileFilter(fileFilter);

        if (isInventaireOuvert()) {
            int rep = fc.showSaveDialog(frame);

            if (rep == JFileChooser.APPROVE_OPTION) {
                File fichier = fc.getSelectedFile();

                String filePath = fichier.getPath();

                if (!filePath.endsWith("txt")) {
                    filePath = filePath.concat(".txt");

                    try {
                        writeFile(filePath);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(frame, "Error");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Aucun inventaire ouvert");
        }
    }

    /*
     * @@@@@@@@@@@@@@
     *
     * @@@ Button @@@
     *
     * @@@@@@@@@@@@@@
     */

    private void btnMoinsInvAction() {
        if (isInventaireOuvert()) {
            int ligneSelectionner = tabInv.getSelectedRow();

            listInventaire.remove(ligneSelectionner);
            update();
        } else {
            JOptionPane.showMessageDialog(frame, "Aucun inventaire ouvert");
        }
    }

    private void btnQuitAction() {
        miQuitAction();
    }

    private void btnMoinsEntAction() {
    }

    private void btnPlusEntAction() {
        ViewAjoutEnt ajout = new ViewAjoutEnt();
    }

    private void btnPlusInvAction() {
        if (isInventaireOuvert() || isNouveau) {
            ViewAjoutInv ajout = new ViewAjoutInv();
            update();
        } else {
            JOptionPane.showMessageDialog(frame, "Aucun inventaire ouvert");
        }
    }

    /*
     * @@@@@@@@@@@@@@@@
     *
     * @@@ Methodes @@@
     *
     * @@@@@@@@@@@@@@@@
     */

    public ArrayList<Inventaire> listInventaire() {
        ArrayList<Inventaire> list = new ArrayList<>();

        return list;
    }

    public boolean isInventaireOuvert() {
        if (modelInv.getRowCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void update() {
        modelInv.setRowCount(0);

        for (Inventaire object : listInventaire) {
            modelInv.addRow(
                    new Object[] { object.getNom(), object.getCategorie(), object.getPrix(),
                            object.getDateAchat(),
                            object.getDescription() });
        }
    }

    public void writeFileObject(String fileName) throws IOException {
        ObjectOutputStream sortie = new ObjectOutputStream(new FileOutputStream(fileName));

        sortie.write(listInventaire.size());
        for (Inventaire object : listInventaire) {
            sortie.writeObject(object);
        }

        sortie.close();
    }

    public void readFileObject(String fileName) throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream entree = new ObjectInputStream(new FileInputStream(fileName));

            listInventaire.clear();
            int nb = entree.read();
            for (int i = 0; i < nb; i++) {
                listInventaire.add((Inventaire) entree.readObject());
            }

            entree.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Could not read file");
        }
    }

    public void writeFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));

        for (int i = 0; i < listInventaire.size(); i++) {
            Inventaire item = listInventaire.get(i);
            writer.write(item.getNom() + ", " + item.getCategorie() + ", " + item.getPrix() + ", " + item.getDateAchat()
                    + ", " + item.getDescription());
            writer.newLine();
            writer.newLine();
        }

        writer.close();
    }

    public static void main(String[] args) throws IOException {
        new ViewInv();
    }
}
