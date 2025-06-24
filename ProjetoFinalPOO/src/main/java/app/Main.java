/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import DAO.MembrosProjetoDAO;
import DAO.ProjetoDAO;
import DAO.TarefaDAO;
import model.StatusTarefa;
import model.Tarefa;
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
        SessaoUsuario.login(usuario);

        System.out.println("\nUsuario logado: "+SessaoUsuario.getUsuarioLogado().getNome());
        System.out.println("Id usuario logado: "+SessaoUsuario.getUsuarioLogado().getIdUsuario());

        ProjetoDAO projetoDAO = new ProjetoDAO();
        MembrosProjetoDAO membrosProjetoDAO = new MembrosProjetoDAO();
        
        Tarefa tarefa = new Tarefa("Tarefa Teste", "Tarefa para testes", "25/06/2025", StatusTarefa.ATRASADA, 1);
    
        TarefaDAO tarefaDAO = new TarefaDAO();
      
    
        //Valores para testes



        //projetoDAO.inserirProjeto(projeto10);
        //membrosProjetoDAO.inserirMembro(10, 1);       

        /*for(Projeto projeto : projetos){
            System.out.println("Tentando inserir: "+projeto.getNome());
            projetoDAO.inserirProjeto(projeto);
        }*/

        TelaPrincipal telaprincipal = new TelaPrincipal();
        telaprincipal.setVisible(true);
    
  }
    
}
