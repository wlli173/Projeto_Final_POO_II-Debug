/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import util.DBSetup;
import view.TelaLogin;
import view.TelaPrincipal;

/**
 *
 * @author Willighan
 */
public class Main {
    
  public static void main(String[] args) {
    
    //Inicializar o banco de dados
    DBSetup.criarTabelas();
    
    /*
    TelaLogin telalogin = new TelaLogin();
    telalogin.setVisible(true);
    */
    
    TelaPrincipal telaprincipal = new TelaPrincipal();
    telaprincipal.setVisible(true);
    
  }
    
}
