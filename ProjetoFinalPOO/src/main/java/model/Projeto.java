package model;

import java.sql.Connection;

import DAO.UsuariosDAO;
import util.ConexaoBD;

public class Projeto {
    
    private int idProjeto;
    private String nome;
    private String descricao;
    private String dataCriacao;
    private String dataFimPrevista;
    private int idLider;

    public Projeto(int idProjeto, String nome, String descricao, String dataCriacao, String dataFimPrevista, int idLider) {
        this.idProjeto = idProjeto;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataFimPrevista = dataFimPrevista;
        this.idLider = idLider;
    }

    public Projeto(String nome, String descricao, String dataCriacao, String dataFimPrevista, int idLider) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataFimPrevista = dataFimPrevista;
        this.idLider = idLider;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(String dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }

    public int getIdLider() {
        return idLider;
    }

    public Usuario getLider() {
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        Usuario lider = usuariosDAO.buscarUsuarioPorId(idLider);

        return lider;

    }

    public void setIdLider(int idLider) {
        this.idLider = idLider;
    }

    public void setLider(Usuario lider) {
        this.idLider = lider.getIdUsuario();
    }

    public int quantidadeTarefas() {
        
        String sql = "SELECT COUNT(*) FROM tarefas WHERE id_projeto = ?";
        
         try(Connection conn = ConexaoBD.getConnection()) {

            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.idProjeto);
            var resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return 0;

    }

    private int quantidadeTarefasConcluidas() {
        
        String sql = "SELECT COUNT(*) FROM tarefas WHERE id_projeto = ? AND status = 'Concluída'";
        
         try(Connection conn = ConexaoBD.getConnection()) {

            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.idProjeto);
            var resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return 0;

    }

    public int porcentagemConcluida() {
        
        int totalTarefas = quantidadeTarefas();
        int tarefasConcluidas = quantidadeTarefasConcluidas();

        if (totalTarefas == 0) {
            return 0; // Evita divisão por zero
        }

        return (tarefasConcluidas * 100) / totalTarefas;

    }

}
