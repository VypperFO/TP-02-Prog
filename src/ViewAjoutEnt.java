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

    Dimension dimTf = new Dimension(250, 25);
    Dimension dimLab = new Dimension(100, 25);
    Dimension dimBtn = new Dimension(100, 25);

    public ViewAjoutEnt() {
        // DIALOG
        dialog = new JDialog((JDialog) null, "Ajout", true);
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

    private void btnAjoutAction() {
        try {
            int selectedRow = ViewInv.tabInv.getSelectedRow();
            String description = txaDescription.getText();
            Date dateRaw = dateChooser.getDate();
            LocalDate date = dateRaw.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
            ViewInv.listInventaire.get(selectedRow).addEntretien(date, description);
            dialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialog, "Une erreur est survenu");
        }
    }

    private void btnCancelAction() {
        dialog.dispose();
    }

    public static void main(String[] args) {
        new ViewAjoutEnt();
    }
}
