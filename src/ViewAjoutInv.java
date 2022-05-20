
/**
 * @author Félix-Olivier Latulippe
 * @DA 2173242
 * @session HV2022
 * 
 * Ce fichier contient le frame d'ajout d'inventaire
 */
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;

import com.toedter.calendar.JDateChooser;

public class ViewAjoutInv {
    static JDialog dialog;

    JLabel labNom, labNumSerie, labCategorie, labPrix, labDate, labDescription;
    JTextField txfNom, txfNumSerie, txfPrix;
    JTextArea txaDescription;
    JButton btnAjout, btnCancel;
    JDateChooser dateChooser;
    JComboBox cmbCategorie;
    JPanel panCenter, panBtn;

    String[] categories = { "Jeux", "Cameras", "Tripod", "Autres" }; // Catégories possibles
    Dimension dimTf = new Dimension(250, 25); // Dimension textfield et textarea
    Dimension dimLab = new Dimension(100, 25); // Dimension labels
    Dimension dimBtn = new Dimension(100, 25); // Dimension bouton

    public ViewAjoutInv() {
        // DIALOG
        dialog = new JDialog((JDialog) null, "Ajout inventaire", true);
        dialog.setTitle("Ajout");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 525);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        // LABEL
        labNom = new JLabel("*Nom:");
        labNom.setPreferredSize(dimLab);
        labNumSerie = new JLabel("No série:");
        labNumSerie.setPreferredSize(dimLab);
        labCategorie = new JLabel("Catégorie:");
        labCategorie.setPreferredSize(dimLab);
        labPrix = new JLabel("*Prix:");
        labPrix.setPreferredSize(dimLab);
        labDate = new JLabel("*Date achat:");
        labDate.setPreferredSize(dimLab);
        labDescription = new JLabel("Description:");
        labDescription.setPreferredSize(dimLab);

        // TEXTFIELD
        txfNom = new JTextField("");
        txfNom.setPreferredSize(dimTf);
        txfNumSerie = new JTextField("");
        txfNumSerie.setPreferredSize(dimTf);
        txfPrix = new JTextField("");
        txfPrix.setPreferredSize(dimTf);

        // COMBO BOX
        cmbCategorie = new JComboBox(categories);
        cmbCategorie.setPreferredSize(dimTf);

        // DATECHOOSER
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(dimTf);

        // TEXTAREA
        txaDescription = new JTextArea("");
        txaDescription.setPreferredSize(new Dimension(250, 250));

        // BOUTON
        btnAjout = new JButton("Ajouter");
        btnAjout.setPreferredSize(dimBtn);
        btnAjout.addActionListener(e -> btnAjoutAction());

        btnCancel = new JButton("Annuler");
        btnCancel.setPreferredSize(dimBtn);
        btnCancel.addActionListener(e -> btnCancelAction());

        // PANEL
        panCenter = new JPanel();
        panCenter.setLayout(new FlowLayout(0, 15, 15));
        panCenter.add(labNom);
        panCenter.add(txfNom);
        panCenter.add(labNumSerie);
        panCenter.add(txfNumSerie);
        panCenter.add(labCategorie);
        panCenter.add(cmbCategorie);
        panCenter.add(labPrix);
        panCenter.add(txfPrix);
        panCenter.add(labDate);
        panCenter.add(dateChooser);
        panCenter.add(labDescription);
        panCenter.add(txaDescription);

        panBtn = new JPanel();
        panBtn.add(btnAjout);
        panBtn.add(btnCancel);

        // DIALOG
        dialog.add(panCenter, BorderLayout.CENTER);
        dialog.add(panBtn, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * Bouton qui permet d'ajouter un objet de classe Inventaire dans l'inventaire
     */
    private void btnAjoutAction() {
        try {
            if (isValide()) {
                try {
                    double prix = Double.parseDouble(txfPrix.getText()); // Prix objet
                    String noSerie = txfNumSerie.getText(); // numéro série objet
                    String nom = txfNom.getText(); // nom objet
                    String description = txaDescription.getText(); // description objet
                    String categorie = cmbCategorie.getSelectedItem().toString(); // catégorie objet
                    Date dateRaw = dateChooser.getDate(); // date complète objet
                    LocalDate date = dateRaw.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // date raffinée
                                                                                                       // et au bon
                                                                                                       // format objet
                    LinkedHashMap<LocalDate, String> entretien = new LinkedHashMap<>(); // entretien objet

                    Inventaire item = new Inventaire(nom, description, categorie, prix, noSerie, date, entretien); // création
                                                                                                                   // du
                                                                                                                   // nouveau
                                                                                                                   // objet
                    ViewInv.listInventaire.add(item);

                    dialog.dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(dialog, "Erreur de donnée");
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Donnée(s) manquante(s)");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialog, "Donnée(s) manquante(s)");
        }
    }

    /**
     * Bouton qui permet d'annuler l'opération
     */
    private void btnCancelAction() {
        dialog.dispose();
    }

    /**
     * Fonction qui permet de vérifier si tout les champs requis sont remplis
     * 
     * @return Retourne true si valide, false si non
     */
    private boolean isValide() {
        String nom = txfNom.getText(); // nom objet
        String prix = txfPrix.getText(); // prix objet
        Date date = dateChooser.getDate(); // date objet

        if (!(nom.equals("") || prix.equals("") || date.equals("") || Double.parseDouble(prix) < 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        new ViewAjoutInv();
    }
}
