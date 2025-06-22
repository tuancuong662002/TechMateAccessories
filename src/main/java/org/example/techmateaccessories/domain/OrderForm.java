package org.example.techmateaccessories.domain;

import org.example.techmateaccessories.domain.dto.OrderItemDTO;

import java.util.List;

public class OrderForm {
    private List<OrderItemDTO> orderItems;
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Bắt buộc phải có Getter và Setter
    public List<OrderItemDTO> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemDTO> orderItems) { this.orderItems = orderItems; }
}