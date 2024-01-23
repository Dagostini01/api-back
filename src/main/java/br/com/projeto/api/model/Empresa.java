package br.com.projeto.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "empresas")
@Getter
@Setter
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descricao;
    private String cnpj;

    public String getNome() {
        return nome;
    }

    public Object getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }
}
