/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.qrcode.controller;

import io.hdmpedro.qrcode.model.QrCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hdmpedro.qrcode.util.GeradorQrCode;
import io.hdmpedro.qrcode.view.MainFrame;
import java.util.Base64;
import javax.swing.ImageIcon;
/**
 *
 * @author DSK-11
 */
public class GerarController {
    private MainFrame view;
    private ObjectMapper jsonMapper;
    private GeradorQrCode qrCodeGenerator;

    public GerarController(MainFrame view) {
        this.view = view;
        this.jsonMapper = new ObjectMapper();
        this.qrCodeGenerator = new GeradorQrCode();
        setupListeners();
    }

    private void setupListeners() {
        view.addGenerateListener(e -> gerarSessao());
    }

    private void gerarSessao() {
        try {
            QrCode data = view.getSessionData();
            validarInput(data);
            
            String json = jsonMapper.writeValueAsString(data);
            String base64 = Base64.getEncoder().encodeToString(json.getBytes());
            ImageIcon qrCode = qrCodeGenerator.gerarQRCode(base64, 200, 200);
            
            view.updateResults(json, base64, qrCode);
        } catch (NumberFormatException ex) {
            view.showError("Porta e ID devem ser números válidos!");
        } catch (IllegalArgumentException ex) {
            view.showError(ex.getMessage());
        } catch (Exception ex) {
            view.showError("Erro ao gerar sessão: " + ex.getMessage());
        }
    }

    private void validarInput(QrCode data) {
        if (data.getHost().isEmpty() || data.getName().isEmpty()) {
            throw new IllegalArgumentException("Preencha todos os campos!");
        }
        if (data.getPort() < 1 || data.getPort() > 65535) {
            throw new IllegalArgumentException("Porta inválida (1-65535)!");
        }
        if (data.getId() < 0) {
            throw new IllegalArgumentException("ID não pode ser negativo!");
        }
    }
}
