/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import DAO.ProjetoDAO;
import DAO.TarefaDAO;
import model.Projeto;
import model.Tarefa;
import view.PainelCriarProjeto;
import view.PainelVisualizarProjeto;
import view.PainelVisualizarTarefa;
import view.TelaPrincipal;

/**
 *
 * @author Aluno
 */
public class NavegacaoMediator {
    
    private TelaPrincipal telaPrincipal;
    
    public NavegacaoMediator(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }
    
    public void abrirVisualizacaoProjeto(Projeto projeto) {
        
        ProjetoDAO projetoDAO = new ProjetoDAO();
        
        projeto.carregarTarefas(projetoDAO);
        
        PainelVisualizarProjeto painel = new PainelVisualizarProjeto(projeto, this);
        painel.setDados(projeto);
        String nomePainel = "VisualizacaoProjeto_" + projeto.getIdProjeto();
        
        telaPrincipal.adicionarEExibirPainel(nomePainel, painel);
        
    }

    public void abrirVisualizacaoTarefa(Tarefa tarefa) {
        
        TarefaDAO tarefaDAO = new TarefaDAO();
        
        PainelVisualizarTarefa painelVisualizarTarefa = new PainelVisualizarTarefa(tarefa);
        painelVisualizarTarefa.setDados();
        painelVisualizarTarefa.carregarComentarios();
        
        String nomePainel = "VisualizacaoTarefa_"+tarefa.getIdTarefa();
        
        telaPrincipal.adicionarEExibirPainel(nomePainel, painelVisualizarTarefa);
        
    }

    public void abrirCriacaoProjeto() {
        
        PainelCriarProjeto painelCriarProjeto = new PainelCriarProjeto(this);
        
        String nomePainel = "CriacaoProjeto";
        
        telaPrincipal.adicionarEExibirPainel(nomePainel, painelCriarProjeto);
        
    }
    
    public TelaPrincipal getTelaPrincipal(){
        return telaPrincipal;
    }
            
    
}
