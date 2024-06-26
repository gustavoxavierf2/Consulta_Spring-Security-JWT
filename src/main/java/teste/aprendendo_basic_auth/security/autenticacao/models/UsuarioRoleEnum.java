package teste.aprendendo_basic_auth.security.autenticacao.models;

public enum UsuarioRoleEnum {
    ADMIN("admin"),
    COMUM("comum");

    private String role;

    UsuarioRoleEnum(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
