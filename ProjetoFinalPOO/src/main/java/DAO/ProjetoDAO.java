package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Projeto;
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
            System.out.println("Erro: " + e.getMessage());
        }

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
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return projetos;
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
