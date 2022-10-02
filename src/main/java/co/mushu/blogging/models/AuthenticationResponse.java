package co.mushu.blogging.models;

public class AuthenticationResponse {
    private final String Jwt;

    public AuthenticationResponse(String jwt){
        this.Jwt = jwt;
    }

    public String getJwt() {
        return Jwt;
    }
}
