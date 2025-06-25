package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Projeto;
import model.StatusTarefa;
import model.Tarefa;
import model.Usuario;
import util.ConexaoBD;

public class TarefaDAO {
    
    public void criarTarefa(Tarefa tarefa) {
        
        String sql = "INSERT INTO tarefas (titulo, descricao, data_fim_prevista, status, id_projeto, id_responsavel) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, tarefa.getTitulo());
            preparedStatement.setString(2, tarefa.getDescricao());
            preparedStatement.setString(3, tarefa.getDataFimPrevisto());
            preparedStatement.setString(4, tarefa.getStatus().getDescricao());
            preparedStatement.setInt(5, tarefa.getIdProjeto());
            preparedStatement.setInt(6, tarefa.getIdUsuarioResponsavel());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO.TarefaDAO.criarTarefa()");
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void atualizarTarefa(Tarefa tarefa) {
        
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, data_fim_prevista = ?, status = ? WHERE id_tarefa = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, tarefa.getTitulo());
            preparedStatement.setString(2, tarefa.getDescricao());
            preparedStatement.setString(3, tarefa.getDataFimPrevisto());
            preparedStatement.setString(4, tarefa.getStatus().getDescricao());
            preparedStatement.setInt(5, tarefa.getIdTarefa());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO.TarefaDAO.atualizarTarefa()");
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void excluirTarefa(int idTarefa) {
        
        String sql = "DELETE FROM tarefas WHERE id_tarefa = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idTarefa);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO.TarefaDAO.excluirTarefa()");
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void excluirTarefa(Tarefa tarefa) {
        excluirTarefa(tarefa.getIdTarefa());
    }

    public void concluirTarefa(int idTarefa) {
        
        String sql = "UPDATE tarefas SET status = 'CONCLUIDA' WHERE id_tarefa = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idTarefa);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO.TarefaDAO.concluirTarefa()");
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void concluirTarefa(Tarefa tarefa) {
        concluirTarefa(tarefa.getIdTarefa());
    }

    public void reabrirTarefa(int idTarefa) {
        
        String sql = "UPDATE tarefas SET status = 'Em Andamento' WHERE id_tarefa = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idTarefa);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO.TarefaDAO.reabrirTarefa()");
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void reabrirTarefa(Tarefa tarefa) {
        reabrirTarefa(tarefa.getIdTarefa());
    }

    public List<Tarefa> listarTarefasPorProjeto(int idProjeto) {
        
        String sql = "SELECT * FROM tarefas WHERE id_projeto = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idProjeto);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Tarefa> tarefas = new ArrayList<>();

            while (resultSet.next()) {
                int idTarefa = resultSet.getInt("id_tarefa");
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                String dataFimPrevisto = resultSet.getString("dataFimPrevisto");
                String statusStr = resultSet.getString("status");
                StatusTarefa status = StatusTarefa.valueOf(statusStr);
                int idResponsavel = resultSet.getInt("id_responsavel");
                
                Tarefa tarefa = new Tarefa(idTarefa, titulo, descricao, dataFimPrevisto, status, idProjeto,idResponsavel);
                tarefas.add(tarefa);
            }

            return tarefas;
            
        } catch (SQLException e) {
            System.out.println("DAO.TarefaDAO.listarTarefasPorProjeto()");
            System.out.println("Erro: " + e.getMessage());
        }

        return new ArrayList<>(); // Retorna uma lista vazia em caso de erro

    }

    public List<Tarefa> listarTarefasPorProjeto(Projeto projeto) {
        return listarTarefasPorProjeto(projeto.getIdProjeto());
    }

    public Tarefa buscarTarefaPorId(int idTarefa) {
        
        String sql = "SELECT * FROM tarefas WHERE id_tarefa = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idTarefa);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                String dataFimPrevisto = resultSet.getString("dataFimPrevisto");
                String statusStr = resultSet.getString("status");
                StatusTarefa status = StatusTarefa.valueOf(statusStr);
                int idProjeto = resultSet.getInt("id_projeto");
                int idResponsavel = resultSet.getInt("id_responsavel");
                
                return new Tarefa(idTarefa, titulo, descricao, dataFimPrevisto, status, idProjeto, idResponsavel);
            }
            
        } catch (SQLException e) {
            System.out.println("DAO.TarefaDAO.buscarTarefaPorId()");
            System.out.println("Erro: " + e.getMessage());
        }

        return null; // Retorna null se a tarefa não for encontrada

    }

    public Tarefa buscarTarefaPorId(Tarefa tarefa) {
        return buscarTarefaPorId(tarefa.getIdTarefa());
    }

    public List<Tarefa> buscarTarefasPorResponsavel(int idResponsavel) {
        
        String sql = "SELECT * FROM tarefas WHERE id_responsavel = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idResponsavel);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Tarefa> tarefas = new ArrayList<>();

            while (resultSet.next()) {
                int idTarefa = resultSet.getInt("idTarefa");
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                String dataFimPrevisto = resultSet.getString("dataFimPrevisto");
                String statusStr = resultSet.getString("status");
                StatusTarefa status = StatusTarefa.valueOf(statusStr);
                int idProjeto = resultSet.getInt("id_projeto");
                
                Tarefa tarefa = new Tarefa(idTarefa, titulo, descricao, dataFimPrevisto, status, idProjeto, idResponsavel);
                tarefas.add(tarefa);
            }

            return tarefas;
            
        } catch (SQLException e) {
            System.out.println("DAO.TarefaDAO.buscarTarefasPorResponsavel()");
            System.out.println("Erro: " + e.getMessage());
        }

        return new ArrayList<>(); // Retorna uma lista vazia em caso de erro

    }

    public List<Tarefa> buscarTarefasPorResponsavel(Usuario responsavel) {
        return buscarTarefasPorResponsavel(responsavel.getIdUsuario());
    }

}
