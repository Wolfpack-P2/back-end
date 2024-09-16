package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payment_table")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payement_txn_id")
    private Integer paymentTxnId;

    @Column(name="failure_reason")
    private String failureReason;

    @Column(name="payment_txn_success")
    private String paymentTxnSuccess;


}
