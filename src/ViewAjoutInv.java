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

    String[] categories = { "Jeux", "Cameras", "Tripod", "Autres" };
    Dimension dimTf = new Dimension(250, 25);
    Dimension dimLab = new Dimension(100, 25);
    Dimension dimBtn = new Dimension(100, 25);

    public ViewAjoutInv() {
        // DIALOG
        dialog = new JDialog((JDialog) null, "Ajout", true);
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

    private void btnAjoutAction() {
        if (isValide()) {
            try {
                double prix = Double.parseDouble(txfPrix.getText());
                Integer noSerie = 0;
                String nom = txfNom.getText();
                String description = txaDescription.getText();
                String categorie = cmbCategorie.getSelectedItem().toString();
                Date dateRaw = dateChooser.getDate();
                LocalDate date = dateRaw.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LinkedHashMap<LocalDate, String> entretien = new LinkedHashMap<>();

                if (txfNumSerie.getText().equals("")) {
                    noSerie = null;
                } else {
                    noSerie = Integer.parseInt(txfNumSerie.getText());
                }

                Inventaire item = new Inventaire(nom, description, categorie, prix, noSerie, date, entretien);
                ViewInv.listInventaire.add(item);

                dialog.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(dialog, "Erreur de donnée");
            }
        } else {
            JOptionPane.showMessageDialog(dialog, "Donnée(s) manquante(s)");
        }
    }

    private void btnCancelAction() {
        dialog.dispose();
    }

    private boolean isValide() {
        String nom = txfNom.getText();
        String prix = txfPrix.getText();
        Date date = dateChooser.getDate();

        if (!(nom.equals("") || prix.equals("") || date.equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        new ViewAjoutInv();
    }
}
