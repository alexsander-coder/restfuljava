package com.first.firstexample.view.model;

public class ProductRequest {
  // #region Atributos
  // private Integer id;
  private String name;
  private Integer quantity;
  private Double value;
  private String description;
  // #endregion

  // #region Getters And Setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  // #endregion
}
