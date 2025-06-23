package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import util.ConexaoBD;

public class MembrosProjetoDAO {
    
    public void inserirMembro(int idProjeto, int idUsuario) {

        String sql = "INSERT INTO membros_projeto (idProjeto, idUsuario) VALUES (?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idProjeto);
            pstmt.setInt(2, idUsuario);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void removerMembro(int idProjeto, int idUsuario) {

        String sql = "DELETE FROM membros_projeto WHERE idProjeto = ? AND idUsuario = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idProjeto);
            pstmt.setInt(2, idUsuario);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public List<Usuario> listarMembros(int idProjeto) {

        List<Usuario> membros = new ArrayList<>();
        String sql = "SELECT u.idUsuario, u.nome, u.email FROM usuarios u " +
                     "JOIN membros_projeto mp ON u.idUsuario = mp.idUsuario " +
                     "WHERE mp.idProjeto = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idProjeto);
            var rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                
                Usuario usuario = new Usuario(idUsuario, nome, email);
                membros.add(usuario);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return membros;

    }

    public Usuario buscarMembros(int idProjeto, int idUsuario) {

        String sql = "SELECT u.idUsuario, u.nome, u.email FROM usuarios u " +
                     "JOIN membros_projeto mp ON u.idUsuario = mp.idUsuario " +
                     "WHERE mp.idProjeto = ? AND mp.idUsuario = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idProjeto);
            pstmt.setInt(2, idUsuario);
            var rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                
                return new Usuario(id, nome, email);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return null;

    }

    public Usuario buscarMembroPorNome(int idProjeto, String nome) {

        String sql = "SELECT u.idUsuario, u.nome, u.email FROM usuarios u " +
                     "JOIN membros_projeto mp ON u.idUsuario = mp.idUsuario " +
                     "WHERE mp.idProjeto = ? AND u.nome = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idProjeto);
            pstmt.setString(2, nome);
            var rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String email = rs.getString("email");
                
                return new Usuario(idUsuario, nome, email);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return null;

    }

}
