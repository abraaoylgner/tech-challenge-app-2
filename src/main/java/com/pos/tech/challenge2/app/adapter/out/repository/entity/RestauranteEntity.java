package com.pos.tech.challenge2.app.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurantes")
@Getter
@Setter
@NoArgsConstructor
public class RestauranteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private EnderecoEmbeddable endereco;

    @Column(nullable = false)
    private String tipoCozinha;

    @Column(nullable = false)
    private String horarioFuncionamento;

    @ManyToOne
    @JoinColumn(name = "dono_id", nullable = false)
    private UsuarioEntity dono;
}