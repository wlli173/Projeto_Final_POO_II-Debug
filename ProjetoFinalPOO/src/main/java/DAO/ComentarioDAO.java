/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Comentario;
import util.ConexaoBD;
import util.ConexaoBD;

/**
 *
 * @author Willighan
 */
public class ComentarioDAO {
    
    public void inserir(Comentario comentario) {
        
        String sql = "INSERT INTO comentarios (id_tarefa, id_autor, conteudo, data_comentario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, comentario.getIdTarefa());
            stmt.setInt(2, comentario.getIdUsuario());
            stmt.setString(3, comentario.getConteudo());
            if (comentario.getDataComentario() != null) {
                stmt.setTimestamp(4, Timestamp.valueOf(comentario.getDataComentario()));
            } else {
                stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir comentário: " + e.getMessage(), e);
        }
    }

    public void atualizar(Comentario comentario) {
        
        String sql = "UPDATE comentarios SET conteudo = ? WHERE id_comentario = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comentario.getConteudo());
            stmt.setInt(2, comentario.getIdComentario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar comentário: " + e.getMessage(), e);
        }
    }

    public void excluir(int idComentario) {
        
        String sql = "DELETE FROM comentarios WHERE id_comentario = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idComentario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir comentário: " + e.getMessage(), e);
        }
    }

    public Comentario buscarPorId(int idComentario) {
        
        String sql = "SELECT * FROM comentarios WHERE id_comentario = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idComentario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearComentario(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar comentário por ID: " + e.getMessage(), e);
        }

        return null;
    }

    public List<Comentario> buscarPorTarefa(int idTarefa) {
        
        String sql = "SELECT * FROM comentarios WHERE id_tarefa = ? ORDER BY data_comentario ASC";
        List<Comentario> comentarios = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTarefa);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comentarios.add(mapearComentario(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar comentários da tarefa: " + e.getMessage(), e);
        }

        return comentarios;
    }
    
    private Comentario mapearComentario(ResultSet rs) throws SQLException {
        
        int idComentario = rs.getInt("id_comentario");
        String conteudo = rs.getString("conteudo");
        String dataComentario = rs.getTimestamp("data_comentario").toString(); // formato ISO yyyy-MM-dd HH:mm:ss
        int idTarefa = rs.getInt("id_tarefa");
        int idUsuario = rs.getInt("id_autor");

        return new Comentario(idComentario, conteudo, dataComentario, idTarefa, idUsuario);
        
}

    
}
