/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Willighan
 */
public class DBSetup {
    
    public static void criarTabelas(){
        
        criarTabelaUsuarios();
        criarTabelaProjetos();
        criarTabelaMembrosProjeto();
        criarTabelaTarefas();
        criarTabelaComentarios();
        
    }
    
    private static void criarTabelaUsuarios(){
        
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "senha_hash TEXT NOT NULL" +
                ");";
        
        executarSQL(sql, "Usuarios");
        
    }
    
    private static void criarTabelaProjetos() {
        
        String sql = "CREATE TABLE IF NOT EXISTS projetos (" +
                "id_projeto INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "descricao TEXT," +
                "data_criacao DATE," +
                "data_fim_prevista DATE," +
                "id_lider INTEGER," +
                "FOREIGN KEY (id_lider) REFERENCES usuarios(id_usuario)" +
                ");";
        
        executarSQL(sql, "projetos");
        
    }
    
    private static void criarTabelaMembrosProjeto() {
        
        String sql = "CREATE TABLE IF NOT EXISTS membros_projeto (" +
                "id_projeto INTEGER NOT NULL," +
                "id_usuario INTEGER NOT NULL," +
                "PRIMARY KEY (id_projeto, id_usuario)," +
                "FOREIGN KEY (id_projeto) REFERENCES projetos(id_projeto)," +
                "FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)" +
                ");";
        
        executarSQL(sql, "membros_projeto");
        
    }
    
    private static void criarTabelaTarefas() {
        
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (" +
                "id_tarefa INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "descricao TEXT," +
                "data_fim_prevista DATE," +
                "status TEXT CHECK(status IN ('Concluída', 'Em Progresso', 'Atrasada')) NOT NULL," +
                "id_projeto INTEGER," +
                "id_responsavel INTEGER," +
                "FOREIGN KEY (id_projeto) REFERENCES projetos(id_projeto)," +
                "FOREIGN KEY (id_responsavel) REFERENCES usuarios(id_usuario)" +
                ");";
        
        executarSQL(sql, "tarefas");
        
    }
    
    private static void criarTabelaComentarios() {
        
        String sql = "CREATE TABLE IF NOT EXISTS comentarios (" +
                "id_comentario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_tarefa INTEGER NOT NULL," +
                "id_autor INTEGER NOT NULL," +
                "conteudo TEXT NOT NULL," +
                "data_comentario TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (id_tarefa) REFERENCES tarefas(id_tarefa)," +
                "FOREIGN KEY (id_autor) REFERENCES usuarios(id_usuario)" +
                ");";
        
        executarSQL(sql, "comentarios");
        
    }
    
    private static void executarSQL(String sql, String nomeTabela){
        
        try {
            
            Connection conn = ConexaoBD.getConnection();
            Statement stmt = conn.createStatement();
            
            stmt.execute(sql);
            System.out.println("Tabela '" + nomeTabela + "' verificada/criada.");

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela '" + nomeTabela + "': " + e.getMessage());
        }
        
    }
    
}
