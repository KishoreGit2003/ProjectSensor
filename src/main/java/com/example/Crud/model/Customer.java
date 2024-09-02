package com.example.Crud.model;

import jakarta.persistence.*;  // Ensure you have the correct JPA import

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "config_id")  // Use @Column to match database column names if necessary
    private String configId;

    @Column(name = "customer_name")  // Ensure column names match the database schema
    private String customerName;

    @Column(name = "application_type")
    private int applicationType;

    @Column(name = "onboarding_date")
    private String onboardingDate;

    @Column(name = "effective_date")
    private String effectiveDate;

    // Default constructor
    public Customer() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(int applicationType) {
        this.applicationType = applicationType;
    }

    public String getOnboardingDate() {
        return onboardingDate;
    }

    public void setOnboardingDate(String onboardingDate) {
        this.onboardingDate = onboardingDate;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
               "id=" + id +
               ", configId=" + configId +
               ", customerName='" + customerName + '\'' +
               ", applicationType=" + applicationType +
               ", onboardingDate='" + onboardingDate + '\'' +
               ", effectiveDate='" + effectiveDate + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
