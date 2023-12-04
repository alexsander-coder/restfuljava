package com.first.firstexample.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.first.firstexample.model.Product;

@Repository
public class ProductRepository_old {
  private List<Product> products = new ArrayList<Product>();
  private Integer ultimoId = 0;

  public List<Product> obterTodos() {
    return products;
  }

  public Optional<Product> obterPorId(Integer id) {
    return products.stream()
        .filter(product -> product.getId() == id)
        .findFirst();
  }

  public Product adicionar(Product product) {
    ultimoId++;
    product.setId(ultimoId);
    products.add(product);

    return product;
  }

  public void deletar(Integer id) {
    products.removeIf(product -> product.getId() == id);

  }

  public Product atualizar(Product product) {
    Optional<Product> produtoEncontrado = obterPorId(product.getId());

    if (produtoEncontrado.isEmpty()) {
      throw new InputMismatchException("Produto não encontrado");
    }

    deletar(product.getId());

    products.add(product);

    return product;

  }
}
