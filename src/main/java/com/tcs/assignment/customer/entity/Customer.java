package com.tcs.assignment.customer.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        private String name;

        private String email;

        @Column(name = "annual_spend")
        private Double annualSpend;

        @Column(name = "last_purchase_date")
        private Date lastPurchaseDate;

        public Customer(){}

        public Customer(UUID id, String name, String email, Double annualSpend, Date lastPurchaseDate) {
                this.id = id;
                this.name = name;
                this.email = email;
                this.annualSpend = annualSpend;
                this.lastPurchaseDate = lastPurchaseDate;
        }

        public UUID getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getEmail() {
                return email;
        }

        public Double getAnnualSpend() {
                return annualSpend;
        }

        public Date getLastPurchaseDate() {
                return lastPurchaseDate;
        }
}
