package com.first.firstexample.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.first.firstexample.model.Product;
import com.first.firstexample.services.ProductService;
import com.first.firstexample.shared.ProductDTO;
import com.first.firstexample.view.model.ProductRequest;
import com.first.firstexample.view.model.ProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductResponse>> obterTodos() {

    List<ProductDTO> products = productService.obterTodos();

    ModelMapper mapper = new ModelMapper();

    List<ProductResponse> resposta = products.stream()
        .map(productDTO -> mapper.map(productDTO, ProductResponse.class))
        .collect(Collectors.toList());

    return new ResponseEntity<>(
        resposta, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<ProductResponse>> obterPorId(@PathVariable Integer id) {
    Optional<ProductDTO> dto = productService.obterPorId(id);

    ProductResponse product = new ModelMapper().map(dto.get(), ProductResponse.class);

    return new ResponseEntity<>(
        Optional.of(product), HttpStatus.OK);

  }

  @PostMapping
  public ResponseEntity<ProductResponse> adicionar(@RequestBody ProductRequest productReq) {
    ModelMapper mapper = new ModelMapper();

    ProductDTO productDTO = mapper.map(productReq, ProductDTO.class);

    productDTO = productService.adicionar(productDTO);

    return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletar(@PathVariable Integer id) {
    productService.deletar(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // return "Produto com id " + id + " foi deletado com sucesso";
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> atualizar(@RequestBody ProductRequest productReq, @PathVariable Integer id) {

    ModelMapper mapper = new ModelMapper();
    ProductDTO productDTO = mapper.map(productReq, ProductDTO.class);

    productDTO = productService.atualizar(id, productDTO);

    return new ResponseEntity<>(
        mapper.map(productDTO, ProductResponse.class),
        HttpStatus.OK);
  }
}
