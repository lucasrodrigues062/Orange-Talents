package br.zup.transacao.transacao.kafka;

import br.zup.transacao.transacao.model.Cartao;

public class CartaoDto {

    private String id;
    private String email;

    public CartaoDto() {
    }
    public Cartao toModel(){
     return new Cartao(this.id, this.email);
    }
    @Override
    public String toString() {
        return "Cartao{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public CartaoDto(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
