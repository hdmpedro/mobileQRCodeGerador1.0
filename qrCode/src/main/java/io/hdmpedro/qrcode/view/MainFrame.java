/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.qrcode.view;

import io.hdmpedro.qrcode.model.QrCode;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author DSK-11
 */

public class MainFrame extends JFrame {
    private JTextField hostField = new JTextField();
    private JTextField portField = new JTextField();
    private JTextField idField = new JTextField();
    private JTextField nameField = new JTextField();

    private JTextArea jsonArea, base64Area;
    private JLabel qrCodeLabel;
    private JButton generateButton;

    public MainFrame() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gerador de Sessão");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createInputPanel(), BorderLayout.NORTH);
        mainPanel.add(createButtonPanel(), BorderLayout.CENTER);
        mainPanel.add(createResultPanel(), BorderLayout.SOUTH);

        add(mainPanel);
        setMinimumSize(new Dimension(500, 600));
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String[] labels = {"Host:", "Porta:", "ID:", "Nome:"};
        JTextField[] fields = {hostField, portField, idField, nameField};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i;
            gbc.gridx = 0;
            panel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            fields[i].setPreferredSize(new Dimension(200, 25)); // Agora seguro
            panel.add(fields[i], gbc);
        }

        return panel;

    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        generateButton = new JButton("Gerar Sessão");
        generateButton.setPreferredSize(new Dimension(150, 30));
        panel.add(generateButton);
        return panel;
    }


    private JPanel createResultPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        jsonArea = new JTextArea(5, 40);
        base64Area = new JTextArea(5, 40);
        qrCodeLabel = new JLabel();

        panel.add(createLabelPanel("JSON:"));
        panel.add(new JScrollPane(jsonArea));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createLabelPanel("Base64:"));
        panel.add(new JScrollPane(base64Area));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createLabelPanel("QR Code:"));
        panel.add(new JScrollPane(qrCodeLabel));

        return panel;
    }



    private JPanel createLabelPanel(String text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(text));
        return panel;
    }

    public void addGenerateListener(java.awt.event.ActionListener listener) {
        generateButton.addActionListener(listener);
    }

    public QrCode getSessionData() throws NumberFormatException {
        return new QrCode(
            hostField.getText(),
            Integer.parseInt(portField.getText()),
            Integer.parseInt(idField.getText()),
            nameField.getText()
        );
    }

    public void updateResults(String json, String base64, ImageIcon qrCode) {
        jsonArea.setText(json);
        base64Area.setText(base64);
        qrCodeLabel.setIcon(qrCode);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
