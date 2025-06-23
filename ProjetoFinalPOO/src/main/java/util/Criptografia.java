package util;

import org.mindrot.jbcrypt.BCrypt;

public class Criptografia {
    
    public static String criptografar(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean verificarSenha(String senha, String hash) {
        return BCrypt.checkpw(senha, hash);
    }

}
