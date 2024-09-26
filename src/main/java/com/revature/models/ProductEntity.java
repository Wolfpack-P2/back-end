package com.revature.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="product_table")
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_name")
    private String productName;

    @Column(name="product_category")
    private String productCategory;

    @Column(name="average_price")
    private Double averagePrice;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(productName, that.productName) && Objects.equals(productCategory, that.productCategory) && Objects.equals(averagePrice, that.averagePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productCategory, averagePrice);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", averagePrice=" + averagePrice +
                '}';
    }
}
