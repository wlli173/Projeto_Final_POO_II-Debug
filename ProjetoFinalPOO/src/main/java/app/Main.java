/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import DAO.MembrosProjetoDAO;
import DAO.ProjetoDAO;
import java.util.List;
import model.Projeto;
import model.Usuario;
import util.DBSetup;
import util.SessaoUsuario;
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
    
    Usuario usuario = new Usuario(1,"willighan", "willighan173@gmail.com", "");
    Usuario usuario1 = new Usuario(1,"willighan", "willighan173@gmail.com", "");
    
    SessaoUsuario.login(usuario);
    
      System.out.println("\nUsuario logado: "+SessaoUsuario.getUsuarioLogado().getNome());
      System.out.println("Id usuario logado: "+SessaoUsuario.getUsuarioLogado().getIdUsuario());
    
    ProjetoDAO projetoDAO = new ProjetoDAO();
    MembrosProjetoDAO membrosProjetoDAO = new MembrosProjetoDAO();
    
    //Valores para testes
        
    Projeto projeto1 = new Projeto("Projeto teste", "Descrição", "22/06/205", "24/06/2025", 1);
    Projeto projeto2 = new Projeto("Projeto teste 2", "Descrição 2", "22/06/205", "24/06/2025", 1);
    Projeto projeto3 = new Projeto("Projeto teste 3", "Descrição 3", "22/06/205", "24/06/2025", 2);
    Projeto projeto4 = new Projeto("Projeto teste 4", "Descrição 4", "22/06/205", "24/06/2025", 1);
    Projeto projeto5 = new Projeto("Projeto teste 5", "Descrição 5", "22/06/205", "24/06/2025", 2);
    Projeto projeto6 = new Projeto("Projeto teste 6", "Descrição 6", "22/06/205", "24/06/2025", 2);
    Projeto projeto7 = new Projeto("Projeto teste 7", "Descrição 7", "22/06/205", "24/06/2025", 1);
    Projeto projeto8 = new Projeto("Projeto teste 8", "Descrição 8", "22/06/205", "24/06/2025", 2);
    Projeto projeto9 = new Projeto("Projeto teste 9", "Líder Id: 2 | Membro Id: 1", "22/06/205", "24/06/2025", 2);
    Projeto projeto10 = new Projeto("Projeto teste 9.2", "Líder Id: 2 | Membro Id: 1", "22/06/205", "24/06/2025", 2);
        
    //projetoDAO.inserirProjeto(projeto10);
    //membrosProjetoDAO.inserirMembro(10, 1);
    
    List<Projeto> projetos = List.of(projeto1, projeto2, projeto3, projeto4, projeto5, projeto6, projeto7, projeto8);        
    
    /*for(Projeto projeto : projetos){
        System.out.println("Tentando inserir: "+projeto.getNome());
        projetoDAO.inserirProjeto(projeto);
    }*/
    
    
    
    TelaPrincipal telaprincipal = new TelaPrincipal();
    telaprincipal.setVisible(true);
    
  }
    
}
