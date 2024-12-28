package com.questonpro.qp_assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryItem, Long> {}