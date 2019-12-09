package com.vbtn.taskunite.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
/**
 * A PaymentInformation.
 */
@Entity
@Table(name = "payment_information")
public class PaymentInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "hold")
    private Double hold;

    @Column(name = "created_at")
@CreationTimestamp
private Instant createdAt;

    @Column(name = "updated_at")
@UpdateTimestamp
private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToOne
    @JoinColumn(unique = true)
    private UserInformation user;

    @OneToMany(mappedBy = "payment")
    private Set<AdminTransaction> adminTransactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public PaymentInformation balance(Double balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getHold() {
        return hold;
    }

    public PaymentInformation hold(Double hold) {
        this.hold = hold;
        return this;
    }

    public void setHold(Double hold) {
        this.hold = hold;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PaymentInformation createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public PaymentInformation updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public PaymentInformation deletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public UserInformation getUser() {
        return user;
    }

    public PaymentInformation user(UserInformation userInformation) {
        this.user = userInformation;
        return this;
    }

    public void setUser(UserInformation userInformation) {
        this.user = userInformation;
    }

    public Set<AdminTransaction> getAdminTransactions() {
        return adminTransactions;
    }

    public PaymentInformation adminTransactions(Set<AdminTransaction> adminTransactions) {
        this.adminTransactions = adminTransactions;
        return this;
    }

    public PaymentInformation addAdminTransactions(AdminTransaction adminTransaction) {
        this.adminTransactions.add(adminTransaction);
        adminTransaction.setPayment(this);
        return this;
    }

    public PaymentInformation removeAdminTransactions(AdminTransaction adminTransaction) {
        this.adminTransactions.remove(adminTransaction);
        adminTransaction.setPayment(null);
        return this;
    }

    public void setAdminTransactions(Set<AdminTransaction> adminTransactions) {
        this.adminTransactions = adminTransactions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentInformation)) {
            return false;
        }
        return id != null && id.equals(((PaymentInformation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PaymentInformation{" +
            "id=" + getId() +
            ", balance=" + getBalance() +
            ", hold=" + getHold() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
