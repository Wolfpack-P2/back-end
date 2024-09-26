package com.revature.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "management")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Management {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "management_id")
    private Integer managementId;

    @Column(name = "payment_type")
    private String paymentType;

    private String country;

    @Column(name = "potential_revenue")
    private BigDecimal potentialRevenue;

    @Column(name = "payment_txn_success")
    private String paymentTxnSuccess;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Management that = (Management) o;
        return Objects.equals(managementId, that.managementId) && Objects.equals(paymentType, that.paymentType) && Objects.equals(country, that.country) && Objects.equals(potentialRevenue, that.potentialRevenue) && Objects.equals(paymentTxnSuccess, that.paymentTxnSuccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managementId, paymentType, country, potentialRevenue, paymentTxnSuccess);
    }

    @Override
    public String toString() {
        return "Management{" +
                "managementId=" + managementId +
                ", paymentType='" + paymentType + '\'' +
                ", country='" + country + '\'' +
                ", potentialRevenue=" + potentialRevenue +
                ", paymentTxnSuccess='" + paymentTxnSuccess + '\'' +
                '}';
    }
}
