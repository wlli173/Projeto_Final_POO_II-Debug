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

        String sql = "INSERT INTO membros_projeto (id_projeto, id_usuario) VALUES (?, ?)";
        
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

        String sql = "DELETE FROM membros_projeto WHERE id_projeto = ? AND id_usuario = ?";
        
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
        String sql = "SELECT u.id_usuario, u.nome, u.email FROM usuarios u " +
                     "JOIN membros_projeto mp ON u.id_usuario = mp.id_usuario " +
                     "WHERE mp.id_projeto = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idProjeto);
            var rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
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

        String sql = "SELECT u.id_usuario, u.nome, u.email FROM usuarios u " +
                     "JOIN membros_projeto mp ON u.id_usuario = mp.id_usuario " +
                     "WHERE mp.id_projeto = ? AND mp.id_usuario = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idProjeto);
            pstmt.setInt(2, idUsuario);
            var rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id_usuario");
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

        String sql = "SELECT u.id_usuario, u.nome, u.email FROM usuarios u " +
                     "JOIN membros_projeto mp ON u.id_usuario = mp.id_usuario " +
                     "WHERE mp.id_projeto = ? AND u.nome = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idProjeto);
            pstmt.setString(2, nome);
            var rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String email = rs.getString("email");
                
                return new Usuario(idUsuario, nome, email);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return null;

    }

}
