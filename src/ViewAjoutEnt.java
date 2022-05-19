/**
 * @author Félix-Olivier Latulippe
 * @DA 2173242
 * @session HV2022
 * 
 * Ce fichier contient le frame d'ajout d'entretiens
 */
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class ViewAjoutEnt {
    JDialog dialog;
    JLabel labDate, labDescription;
    JDateChooser dateChooser;
    JTextArea txaDescription;
    JButton btnAjout, btnCancel;
    JPanel panCenter, panBtn;

    Dimension dimTf = new Dimension(250, 25); // Dimension textfield et textarea
    Dimension dimLab = new Dimension(100, 25); // Dimension labels
    Dimension dimBtn = new Dimension(100, 25); // Dimension boutons

    public ViewAjoutEnt() {
        // DIALOG
        dialog = new JDialog((JDialog) null, "Ajout entretien", true);
        dialog.setTitle("Ajouter");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(null);

        // LABEL
        labDate = new JLabel("*Date:");
        labDate.setPreferredSize(dimLab);
        labDescription = new JLabel("*Description:");
        labDescription.setPreferredSize(dimLab);

        // DATE CHOOSER
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(dimTf);

        // TEXTAREA
        txaDescription = new JTextArea();
        txaDescription.setPreferredSize(new Dimension(250, 250));

        // BOUTONS
        btnAjout = new JButton("Ajouter");
        btnAjout.setPreferredSize(dimBtn);
        btnAjout.addActionListener(e -> btnAjoutAction());
        btnCancel = new JButton("Annuler");
        btnCancel.setPreferredSize(dimBtn);
        btnCancel.addActionListener(e -> btnCancelAction());

        // PANEL
        panCenter = new JPanel();
        panCenter.setLayout(new FlowLayout(0, 15, 15));
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
     * Bouton qui permet d'ajout un entretien dans un objet d'inventaire
     */
    private void btnAjoutAction() {
        try {
            if (isValide()) {
                int selectedRow = ViewInv.tabInv.getSelectedRow(); // Ligne sélectionner dans l'inventaire
                String description = txaDescription.getText(); // description choisit
                Date dateRaw = dateChooser.getDate(); // Date complète choisit
                LocalDate date = dateRaw.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Date raffinée et au bon format
    
                ViewInv.listInventaire.get(selectedRow).addEntretien(date, description);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Donnée(s) manquante(s)");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialog, "Une erreur est survenu");
        }
    }

    /**
     * Fonction qui permet de vérifier si tout les champs requis sont remplis
     * @return Retourne true si valide, false si non
     */
    private boolean isValide() {
        String description = txaDescription.getText(); // description entretien
        Date date = dateChooser.getDate(); // date entretien

        if (!(description.equals("") || date.equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Bouton qui permet d'annuler l'opération
     */
    private void btnCancelAction() {
        dialog.dispose();
    }

    public static void main(String[] args) {
        new ViewAjoutEnt();
    }
}
