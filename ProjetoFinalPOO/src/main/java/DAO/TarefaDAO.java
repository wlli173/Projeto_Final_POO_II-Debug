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
        
        String sql = "INSERT INTO Tarefa (titulo, descricao, dataFimPrevisto, status, id_projeto) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, tarefa.getTitulo());
            preparedStatement.setString(2, tarefa.getDescricao());
            preparedStatement.setString(3, tarefa.getDataFimPrevisto());
            preparedStatement.setString(4, tarefa.getStatus().toString());
            preparedStatement.setInt(5, tarefa.getIdProjeto());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void atualizarTarefa(Tarefa tarefa) {
        
        String sql = "UPDATE Tarefa SET titulo = ?, descricao = ?, dataFimPrevisto = ?, status = ? WHERE idTarefa = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, tarefa.getTitulo());
            preparedStatement.setString(2, tarefa.getDescricao());
            preparedStatement.setString(3, tarefa.getDataFimPrevisto());
            preparedStatement.setString(4, tarefa.getStatus().toString());
            preparedStatement.setInt(5, tarefa.getIdTarefa());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void excluirTarefa(int idTarefa) {
        
        String sql = "DELETE FROM Tarefa WHERE idTarefa = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idTarefa);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void excluirTarefa(Tarefa tarefa) {
        excluirTarefa(tarefa.getIdTarefa());
    }

    public void concluirTarefa(int idTarefa) {
        
        String sql = "UPDATE Tarefa SET status = 'CONCLUIDA' WHERE idTarefa = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idTarefa);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void concluirTarefa(Tarefa tarefa) {
        concluirTarefa(tarefa.getIdTarefa());
    }

    public void reabrirTarefa(int idTarefa) {
        
        String sql = "UPDATE Tarefa SET status = 'PENDENTE' WHERE idTarefa = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idTarefa);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void reabrirTarefa(Tarefa tarefa) {
        reabrirTarefa(tarefa.getIdTarefa());
    }

    public List<Tarefa> listarTarefasPorProjeto(int idProjeto) {
        
        String sql = "SELECT * FROM Tarefa WHERE id_projeto = ?";

        try (Connection connection = ConexaoBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, idProjeto);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Tarefa> tarefas = new ArrayList<>();

            while (resultSet.next()) {
                int idTarefa = resultSet.getInt("idTarefa");
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                String dataFimPrevisto = resultSet.getString("dataFimPrevisto");
                String statusStr = resultSet.getString("status");
                StatusTarefa status = StatusTarefa.valueOf(statusStr);
                
                Tarefa tarefa = new Tarefa(idTarefa, titulo, descricao, dataFimPrevisto, status, idProjeto);
                tarefas.add(tarefa);
            }

            return tarefas;
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return new ArrayList<>(); // Retorna uma lista vazia em caso de erro

    }

    public List<Tarefa> listarTarefasPorProjeto(Projeto projeto) {
        return listarTarefasPorProjeto(projeto.getIdProjeto());
    }

    public Tarefa buscarTarefaPorId(int idTarefa) {
        
        String sql = "SELECT * FROM Tarefa WHERE idTarefa = ?";

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
                
                return new Tarefa(idTarefa, titulo, descricao, dataFimPrevisto, status, idProjeto);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return null; // Retorna null se a tarefa não for encontrada

    }

    public Tarefa buscarTarefaPorId(Tarefa tarefa) {
        return buscarTarefaPorId(tarefa.getIdTarefa());
    }

    public List<Tarefa> buscarTarefasPorResponsavel(int idResponsavel) {
        
        String sql = "SELECT * FROM Tarefa WHERE idResponsavel = ?";

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
                
                Tarefa tarefa = new Tarefa(idTarefa, titulo, descricao, dataFimPrevisto, status, idProjeto);
                tarefas.add(tarefa);
            }

            return tarefas;
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return new ArrayList<>(); // Retorna uma lista vazia em caso de erro

    }

    public List<Tarefa> buscarTarefasPorResponsavel(Usuario responsavel) {
        return buscarTarefasPorResponsavel(responsavel.getIdUsuario());
    }

}
