import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class ViewModifInv {
    JDialog dialog;

    JLabel labNom, labNumSerie, labCategorie, labPrix, labDate, labDescription;
    JTextField txfNom, txfNumSerie, txfPrix;
    JTextArea txaDescription;
    JButton btnModif, btnCancel;
    JDateChooser dateChooser;
    JComboBox cmbCategorie;
    JPanel panCenter, panBtn;

    String[] categories = { "Jeux", "Cameras", "Salopes" };
    Dimension dimTf = new Dimension(250, 25);
    Dimension dimLab = new Dimension(100, 25);
    Dimension dimBtn = new Dimension(100, 25);

    private int selectedRow = ViewInv.tabInv.getSelectedRow();

    public ViewModifInv() {
        // DIALOG
        dialog = new JDialog((JDialog) null, "Modifier", true);
        dialog.setTitle("Modifier");
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
        String nom = ViewInv.listInventaire.get(selectedRow).getNom();
        String noSerie = String.valueOf(ViewInv.listInventaire.get(selectedRow).getNumSerie());
        String prix = String.valueOf(ViewInv.listInventaire.get(selectedRow).getPrix());

        if (noSerie.equals("null"))
            noSerie = "";

        txfNom = new JTextField(nom);
        txfNom.setPreferredSize(dimTf);
        txfNumSerie = new JTextField(noSerie);
        txfNumSerie.setPreferredSize(dimTf);
        txfPrix = new JTextField(prix);
        txfPrix.setPreferredSize(dimTf);

        // COMBOBOX
        String categorie = ViewInv.listInventaire.get(selectedRow).getCategorie();
        cmbCategorie = new JComboBox(categories);
        cmbCategorie.setSelectedItem(categorie);
        cmbCategorie.setPreferredSize(dimTf);

        // DATE CHOOSER
        LocalDate dateRaw = ViewInv.listInventaire.get(selectedRow).getDateAchat();
        Date date = Date.from(dateRaw.atStartOfDay(ZoneId.systemDefault()).toInstant());

        dateChooser = new JDateChooser(date);
        dateChooser.setPreferredSize(dimTf);

        // TEXTAREA
        String description = ViewInv.listInventaire.get(selectedRow).getDescription();
        txaDescription = new JTextArea(description);
        txaDescription.setPreferredSize(new Dimension(250, 250));

        // BOUTONS
        btnModif = new JButton("Modifier");
        btnModif.setPreferredSize(dimBtn);
        btnModif.addActionListener(e -> btnModifAction());

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
        panBtn.add(btnModif);
        panBtn.add(btnCancel);

        // DIALOG
        dialog.add(panCenter, BorderLayout.CENTER);
        dialog.add(panBtn, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void btnModifAction() {
        try {
            if (isValide()) {
                try {
                    String nom = txfNom.getText();
                    String description = txaDescription.getText();
                    String categorie = cmbCategorie.getSelectedItem().toString();
                    Integer noSerie = Integer.parseInt(txfNumSerie.getText());
                    double prix = Double.parseDouble(txfPrix.getText());
                    Date dateRaw = dateChooser.getDate();
                    LocalDate date = dateRaw.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
                    if (txfNumSerie.getText().equals("")) {
                        noSerie = null;
                    } else {
                        noSerie = Integer.parseInt(txfNumSerie.getText());
                    }
    
                    Inventaire item = ViewInv.listInventaire.get(selectedRow);
    
                    item.setNom(nom);
                    item.setDescription(description);
                    item.setCategorie(categorie);
                    item.setNumSerie(noSerie);
                    item.setPrix(prix);
                    item.setDateAchat(date);
    
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

    private void btnCancelAction() {
        dialog.dispose();
    }

    public static void main(String[] args) {
        new ViewModifInv();
    }
}
