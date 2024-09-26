package com.revature.models;

import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "marketing")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Marketing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marketing_id")
    private Integer marketingId;

    @Column(name = "product_category")
    private String productCategory;

    private Integer qty;

    @Column(name = "order_date")
    private Timestamp orderDate;

    private String country;

    private String city;

    @Column(name = "time_of_day")
    private Integer timeOfDay;

    private Integer months;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marketing marketing = (Marketing) o;
        return Objects.equals(marketingId, marketing.marketingId) && Objects.equals(productCategory, marketing.productCategory) && Objects.equals(qty, marketing.qty) && Objects.equals(orderDate, marketing.orderDate) && Objects.equals(country, marketing.country) && Objects.equals(city, marketing.city) && Objects.equals(timeOfDay, marketing.timeOfDay) && Objects.equals(months, marketing.months);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marketingId, productCategory, qty, orderDate, country, city, timeOfDay, months);
    }

    @Override
    public String toString() {
        return "Marketing{" +
                "marketingId=" + marketingId +
                ", productCategory='" + productCategory + '\'' +
                ", qty=" + qty +
                ", orderDate=" + orderDate +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", timeOfDay=" + timeOfDay +
                ", months=" + months +
                '}';
    }
}
