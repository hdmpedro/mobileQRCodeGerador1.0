/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.qrcode.model;

/**
 *
 * @author DSK-11
 */
public class QrCode {
    
    private String host;
    private int port;
    private long id;
    private String name;

    public QrCode() {
    }

    public QrCode(String host, int port, long id, String nome) {
        this.host = host;
        this.port = port;
        this.id = id;
        this.name = nome;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;

    }
}