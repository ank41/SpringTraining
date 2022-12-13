package ru.kakaulin.financialApp.dto;

import java.util.List;

public class OrderResponse {

    List<OrderDTO> orders;

    public OrderResponse(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
