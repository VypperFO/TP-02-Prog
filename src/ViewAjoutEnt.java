import javax.swing.*;
import java.awt.*;

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

    private void btnCancelAction() {
        dialog.dispose();
    }

    public static void main(String[] args) {
        new ViewAjoutEnt();
    }
}
