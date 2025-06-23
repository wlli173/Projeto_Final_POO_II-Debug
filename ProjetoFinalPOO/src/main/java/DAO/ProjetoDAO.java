package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Projeto;
import util.ConexaoBD;

public class ProjetoDAO {
    
    public void inserirProjeto(Projeto projeto) {
        
        String sql = "INSERT INTO projeto (nome, descricao, dataCriacao, dataFimPrevista, idLider) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = ConexaoBD.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, projeto.getNome());
            pstmt.setString(2, projeto.getDescricao());
            pstmt.setString(3, projeto.getDataCriacao());
            pstmt.setString(4, projeto.getDataFimPrevista());
            pstmt.setInt(5, projeto.getIdLider());  

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void atualizarProjeto(Projeto projeto) {
        
        String sql = "UPDATE projeto SET nome = ?, descricao = ?, dataFimPrevista = ? WHERE idProjeto = ?";

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
        
        String sql = "DELETE FROM projeto WHERE idProjeto = ?";

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
        
        String sql = "SELECT * FROM projeto WHERE idProjeto = ?";
        Projeto projeto = null;

        try(Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProjeto);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Projeto(
                    rs.getInt("idProjeto"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getString("dataCriacao"),
                    rs.getString("dataFimPrevista"),
                    rs.getInt("idLider")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return projeto;

    }

    public void buscarProjetoPorNome(String nome) {
        
        String sql = "SELECT * FROM projeto WHERE nome LIKE ?";
        nome = "%" + nome + "%";
        Projeto projeto = null;

        try(Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                projeto = new Projeto(
                    rs.getInt("idProjeto"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getString("dataCriacao"),
                    rs.getString("dataFimPrevista"),
                    rs.getInt("idLider")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public List<Projeto> listarProjetos() {
        
        String sql = "SELECT * FROM projeto";
        List<Projeto> projetos = new ArrayList<>();

        try(Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                projetos.add(new Projeto(
                    rs.getInt("idProjeto"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getString("dataCriacao"),
                    rs.getString("dataFimPrevista"),
                    rs.getInt("idLider")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return projetos;

    }

    

}
