/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import DAO.ProjetoDAO;
import model.Projeto;
import view.PainelVisualizarProjeto;
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
    
}
