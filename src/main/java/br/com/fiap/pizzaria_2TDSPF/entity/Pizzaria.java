package br.com.fiap.pizzaria_2TDSPF.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_2TDSPF_PIZZARIA")
public class Pizzaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PIZZARIA")
    @SequenceGenerator(
            name = "SQ_PIZZARIA",
            sequenceName = "SQ_PIZZARIA",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_PIZZARIA")
    private Long id;

    @Column(name = "NM_PIZZARIA")
    private String nome;

    // 1 pizzaria deve ter apenas um cardapio
    // mas um cardápio pode ser utilizado em várias pizzarias
    //Cuidado a classe da lista não é cardápio. É produto 😯!
    //Então vamos analisar novamente?

    // 1 pizzaria pode ter muitos produtos
    // e um mesmo produto pode ser oferecido para muitas pizzarias.
    //Portanto a relação é de Muitos para Muitos.

    //Geralmente, quando o tipo é uma Collection o relacionamento é de
    //Muitos para Muitos
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_2TDSPF_CARDAPIO",
            joinColumns = {
                    @JoinColumn(
                            name = "PIZZARIA",
                            referencedColumnName = "ID_PIZZARIA",
                            foreignKey = @ForeignKey(
                                    name = "FK_CARDAPIO_PIZZARIA"
                            )
                    )
            },
            inverseJoinColumns = {
                  @JoinColumn(
                          name = "PRODUTO",
                          referencedColumnName = "ID_PRODUTO",
                          foreignKey = @ForeignKey(
                                  name = "FK_PIZZARIA_CARDAPIO"
                          )
                  )
            }
    )
    private Set<Produto> cardapio = new LinkedHashSet<>();

}
