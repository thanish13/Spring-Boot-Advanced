package org.t13.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.t13.app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
