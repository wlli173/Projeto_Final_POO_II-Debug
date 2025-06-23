package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import util.ConexaoBD;

public class UsuariosDAO {
    
    public void inserirUsuario(String nome, String email, String senhaHash) {
        
        String sql = "INSERT INTO usuarios (nome, email, senha_hash) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senhaHash);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuarios() {
        
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            var rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senhaHash = rs.getString("senha_hash");
                
                Usuario usuario = new Usuario(idUsuario, nome, email, senhaHash);
                usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return usuarios;

    }

    public void atualizarUsuario(Usuario usuario) {
        
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha_hash = ? WHERE id_usuario = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenhaHash());
            pstmt.setInt(4, usuario.getIdUsuario());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void excluirUsuario(int idUsuario) {
        
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idUsuario);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void excluirUsuario(Usuario usuario) {
        excluirUsuario(usuario.getIdUsuario());
    }
    
    public Usuario buscarUsuarioPorId(int idUsuario) {
        
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        Usuario usuario = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, idUsuario);
            var rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senhaHash = rs.getString("senha_hash");
                
                usuario = new Usuario(idUsuario, nome, email, senhaHash);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return usuario;
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        
        String sql = "SELECT * FROM usuarios WHERE nome LIKE ?";
        nome = "%" + nome + "%";
        Usuario usuario = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, nome);
            var rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String email = rs.getString("email");
                String senhaHash = rs.getString("senha_hash");
                
                usuario = new Usuario(idUsuario, nome, email, senhaHash);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return usuario;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usuario = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, email);
            var rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                String senhaHash = rs.getString("senha_hash");
                
                usuario = new Usuario(idUsuario, nome, email, senhaHash);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return usuario;
    }

}
