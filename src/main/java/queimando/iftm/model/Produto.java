package queimando.iftm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private Long id;
    private String nomep;
    private Double preco;
    private String foto;
    private String endereco;
    private Usuario proprietario;
}