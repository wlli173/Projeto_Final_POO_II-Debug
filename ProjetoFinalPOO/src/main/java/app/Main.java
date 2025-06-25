/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import DAO.MembrosProjetoDAO;
import DAO.ProjetoDAO;
import DAO.TarefaDAO;
import DAO.UsuariosDAO;
import java.util.List;
import model.Projeto;
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
    
    private void depurarTarefas(){
        
        System.out.println("\nDepuracao Tarefas\n");
        
        int idUsuarioLogado = SessaoUsuario.getUsuarioLogado().getIdUsuario();
        ProjetoDAO projetoDAO = new ProjetoDAO();
        List<Projeto> projetos;
        
        projetos = projetoDAO.buscarProjetosDoMembro(idUsuarioLogado);
        
        if(!projetos.isEmpty()){
            
            for(Projeto projeto : projetos){
                
            System.out.println("Projeto:"+projeto.getNome());
            
            projeto.carregarTarefasDoUsuario(idUsuarioLogado, projetoDAO);
            
            if(projeto.getTarefas().isEmpty()){
                System.out.println("List de tarefas do usuario no projeto: "+projeto.getNome()+" vazio");
            }
            
            for(Tarefa tarefa : projeto.getTarefas()){
                System.out.println("└── Tarefa:"+tarefa.getTitulo());
            }
            
        }
            
        }else{
            System.out.println("List de projetos do membro vazio");
        }
        
        System.out.println("\nFIM ------- Depuracao Tarefas\n");
        
    }
    
    private void depurarLogin(){
        System.out.println("\nDepuracao Login");
        System.out.println("\nUsuario logado: "+SessaoUsuario.getUsuarioLogado().getNome());
        System.out.println("Id usuario logado: "+SessaoUsuario.getUsuarioLogado().getIdUsuario()+"\n");
    }
    
  public static void main(String[] args) {
    
        //Inicializar o banco de dados
        DBSetup.criarTabelas();

        /* TODO HABILITAR NOVAMENTE ao final dos testes
        TelaLogin telalogin = new TelaLogin();
        telalogin.setVisible(true);
        */

        Usuario usuario = new Usuario(1,"willighan", "willighan173@gmail.com", "");
        SessaoUsuario.login(usuario);
       
        
        TelaPrincipal telaprincipal = new TelaPrincipal();
        telaprincipal.setVisible(true);
    
  }
    
}
