package queimando.iftm.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import queimando.iftm.model.Produto;

@Repository
public class ProdutoRepository {
  @Autowired
  JdbcTemplate jdbc;

  public void save(Produto produto) {
    String sql = "insert into produto(nome_produto, preço, foto, endereço, id) values (?,?,?,?,?);";
    jdbc.update(sql, produto.getNomep(), produto.getPreco(), produto.getFoto(), produto.getEndereco(), produto.getId());
  }

  public List<Produto> findAll() {
    return jdbc.query("select * from produto;", (registro, contador) -> {
      Produto produto = new Produto();
      produto.setId(registro.getLong("cod_produto"));
      produto.setNomep(registro.getString("nome_produto"));
      produto.setPreco(registro.getDouble("preço"));
      produto.setFoto(registro.getString("foto"));
      produto.setEndereco(registro.getString("endereço"));
      return produto;
    });
  }

  public Produto findById(Long id) {
    String sql = "select * from produto where cod_produto=?;";
    return jdbc.queryForObject(
        sql,
        (registro, contador) -> {
          Produto produto = new Produto();
          produto.setId(registro.getLong("cod_produto"));
          produto.setNomep(registro.getString("nome_produto"));
          produto.setPreco(registro.getDouble("preço"));
          produto.setFoto(registro.getString("foto"));
          produto.setEndereco(registro.getString("endereço"));
          return produto;
        },
        id);
  }

}
