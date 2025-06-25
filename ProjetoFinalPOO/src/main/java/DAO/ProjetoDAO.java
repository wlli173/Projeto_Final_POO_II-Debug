package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Projeto;
import model.StatusTarefa;
import model.Tarefa;
import model.Usuario;
import util.ConexaoBD;

public class ProjetoDAO {
    
    public void inserirProjeto(Projeto projeto) {
        
        String sql = "INSERT INTO projetos (nome, descricao, data_criacao, data_fim_prevista, id_lider) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = ConexaoBD.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, projeto.getNome());
            pstmt.setString(2, projeto.getDescricao());
            pstmt.setString(3, projeto.getDataCriacao());
            pstmt.setString(4, projeto.getDataFimPrevista());
            pstmt.setInt(5, projeto.getIdLider());  
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.inserirProjeto()");
            System.out.println("Erro: " + e.getMessage());
        }

    }
    
    public int inserirProjetoRetornandoId(Projeto projeto) {

        String sql = "INSERT INTO projetos (nome, descricao, data_criacao, data_fim_prevista, id_lider) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, projeto.getNome());
            pstmt.setString(2, projeto.getDescricao());
            pstmt.setString(3, projeto.getDataCriacao());
            pstmt.setString(4, projeto.getDataFimPrevista());
            pstmt.setInt(5, projeto.getIdLider());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // Usar função SQLite para obter o último ID inserido nesta conexão
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
                    if (rs.next()) {
                        return rs.getInt(1); // Retorna o ID gerado
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.inserirProjetoRetornandoId()");
            System.out.println("Erro: " + e.getMessage());
        }

        return -1; // Em caso de erro
    }



    public void atualizarProjeto(Projeto projeto) {
        
        String sql = "UPDATE projetos SET nome = ?, descricao = ?, dataFimPrevista = ? WHERE id_projeto = ?";

        try(Connection conn = ConexaoBD.getConnection();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, projeto.getNome());
            pstmt.setString(2, projeto.getDescricao());
            pstmt.setString(3, projeto.getDataFimPrevista());
            pstmt.setInt(4, projeto.getIdProjeto());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.atualizarProjeto()");
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void excluirProjeto(int idProjeto) {
        
        String sql = "DELETE FROM projetos WHERE id_projeto = ?";

        try(Connection conn = ConexaoBD.getConnection();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProjeto);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.excluirProjeto()");
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void excluirProjeto(Projeto projeto) {
        excluirProjeto(projeto.getIdProjeto());
    }

    public Projeto buscarProjetoPorId(int idProjeto) {
        
        String sql = "SELECT * FROM projetos WHERE id_projeto = ?";
        Projeto projeto = null;

        try(Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProjeto);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                projeto = construirProjeto(rs);
            }

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.buscarProjetoPorId()");
            System.out.println("Erro: " + e.getMessage());
        }

        return projeto;

    }

    public Projeto buscarProjetoPorNome(String nome) {
        
        String sql = "SELECT * FROM projetos WHERE nome LIKE ?";
        nome = "%" + nome + "%";
        Projeto projeto = null;

        try(Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                projeto = construirProjeto(rs);
            }

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.buscarProjetoPorNome()");
            System.out.println("Erro: " + e.getMessage());
        }
        
        return projeto;

    }

    public List<Projeto> listarProjetos() {
        
        String sql = "SELECT * FROM projetos";
        List<Projeto> projetos = new ArrayList<>();

        try(Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Projeto projeto = construirProjeto(rs);
                projetos.add(projeto);
            }

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.listarProjetos()");
            System.out.println("Erro: " + e.getMessage());
        }

        return projetos;

    }

    public List<Projeto> buscarProjetosDoLider(int idLider) {
        
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projetos WHERE id_lider = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLider);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Projeto projeto = construirProjeto(rs);
                projetos.add(projeto);
            }

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.buscarProjetosDoLider()");
            System.out.println("Erro: " + e.getMessage());
        }

        return projetos;
    
    }

    public List<Projeto> buscarProjetosDoMembro(int idUsuario) {
        
        List<Projeto> projetos = new ArrayList<>();
        String sql = """
            SELECT p.*
            FROM projetos p
            INNER JOIN membros_projeto mp ON p.id_projeto = mp.id_projeto
            WHERE mp.id_usuario = ?
            """;

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Projeto projeto = construirProjeto(rs);
                projetos.add(projeto);
            }

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.buscarProjetosDoMembro()");
            System.out.println("Erro: " + e.getMessage());
        }

        return projetos;
    }

    public int quantidadeTarefas(int idProjeto) {
        
        String sql = "SELECT COUNT(*) FROM tarefas WHERE id_projeto = ?";
        
         try(Connection conn = ConexaoBD.getConnection()) {

            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, idProjeto);
            var resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;

        } catch (Exception e) {
            System.out.println("DAO.ProjetoDAO.quantidadeTarefas()");
            System.out.println("Erro: " + e.getMessage());
        }

        return 0;

    }

    public int quantidadeTarefasConcluidas(int idProjeto) {
        
         // Método para contar as tarefas concluídas de um projeto específico
         // Retorna o número de tarefas concluídas
         // Se não houver tarefas, retorna 0
        
        String sql = "SELECT COUNT(*) FROM tarefas WHERE id_projeto = ? AND status = 'Concluída'";
        
         try(Connection conn = ConexaoBD.getConnection()) {

            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, idProjeto);
            var resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;

        } catch (Exception e) {
            System.out.println("DAO.ProjetoDAO.quantidadeTarefasConcluidas()");
            System.out.println("Erro: " + e.getMessage());
        }

        return 0;

    }

    public List<Tarefa> buscarTarefasDoProjeto(int idProjeto) {
        
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE id_projeto = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                    rs.getInt("id_tarefa"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("data_fim_prevista"),
                    StatusTarefa.fromDescricao(rs.getString("status")),
                    rs.getInt("id_projeto"),
                    rs.getInt("id_responsavel")
                );
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.buscarTarefasDoProjeto()");
            System.out.println("Erro: " + e.getMessage());
        }

        return tarefas;
    }

    // Método para buscar as tarefas de um usuário específico em um projeto
    // Retorna uma lista de tarefas atribuídas ao usuário no projeto especificado
    public List<Tarefa> buscarTarefasDoUsuario (int idUsuario, int idProjeto) {
        
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = """
            SELECT t.*
            FROM tarefas t
            INNER JOIN membros_projeto mp ON t.id_projeto = mp.id_projeto
            WHERE mp.id_usuario = ? AND t.id_projeto = ?
            """;

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idProjeto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                    rs.getInt("id_tarefa"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("data_fim_prevista"),
                    StatusTarefa.fromDescricao(rs.getString("status")),
                    rs.getInt("id_projeto"),
                    rs.getInt("id_responsavel")
                );
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            System.out.println("DAO.ProjetoDAO.buscarTarefasDoUsuario()");
            System.out.println("Erro: " + e.getMessage());
        }

        return tarefas;
    }

    private Projeto construirProjeto(ResultSet rs) throws SQLException {
        
        int idProjeto = rs.getInt("id_projeto");
        String nome = rs.getString("nome");
        String descricao = rs.getString("descricao");
        String dataCriacao = rs.getString("data_criacao");
        String dataFim = rs.getString("data_fim_prevista");
        int idLider = rs.getInt("id_lider");

        Usuario lider = new UsuariosDAO().buscarUsuarioPorId(idLider);

        return new Projeto(idProjeto, nome, descricao, dataCriacao, dataFim, lider.getIdUsuario());
        
    }

}
