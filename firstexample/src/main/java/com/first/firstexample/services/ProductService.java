package com.first.firstexample.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.first.firstexample.model.Product;
import com.first.firstexample.model.exception.ResourceNotFoundException;
import com.first.firstexample.repository.ProductRepository;
import com.first.firstexample.shared.ProductDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<ProductDTO> obterTodos() {

    List<Product> products = productRepository.findAll();

    return products.stream()
        .map(product -> new ModelMapper().map(product, ProductDTO.class))
        .collect(Collectors.toList());
  }

  public Optional<ProductDTO> obterPorId(Integer id) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isEmpty()) {
      throw new ResourceNotFoundException("produto com id " + id + " não encontrado");
    }

    ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
    return Optional.of(dto);
  }

  public ProductDTO adicionar(ProductDTO productDTO) {

    productDTO.setId(null);

    ModelMapper mapper = new ModelMapper();

    Product product = mapper.map(productDTO, Product.class);

    product = productRepository.save(product);

    productDTO.setId(product.getId());

    return productDTO;
  }

  public void deletar(Integer id) {
    Optional<Product> product = productRepository.findById(id);

    if (product.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível deletar o produto com o id: " + id);
    }

    productRepository.deleteById(id);
  }

  public ProductDTO atualizar(Integer id, ProductDTO productDTO) {
    productDTO.setId(id);

    ModelMapper mapper = new ModelMapper();

    Product product = mapper.map(productDTO, Product.class);

    productRepository.save(product);

    return productDTO;
  }
}
