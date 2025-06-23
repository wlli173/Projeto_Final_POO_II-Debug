package controle;

import DAO.UsuariosDAO;
import model.Usuario;
import util.Criptografia;

public class ControleLogin {
    
    public static boolean autenticar(String email, String senha) {
        
        UsuariosDAO usuarioDAO = new UsuariosDAO();
        Usuario usuario = usuarioDAO.buscarUsuarioPorEmail(email);

        if (usuario == null) {
            return false; // Usuário não encontrado
        }

        //Criptografa a senha fornecida pelo usuário
        String senhaCriptografada = Criptografia.criptografar(senha);
            
        return Criptografia.verificarSenha(senha, senhaCriptografada);

    }

}
