package org.example.techmateaccessories.domain.dto;

public class OrderItemDTO {
    private Long productId;
    private int quantity;



    // Bắt buộc phải có Getters và Setters để Spring hoạt động
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}