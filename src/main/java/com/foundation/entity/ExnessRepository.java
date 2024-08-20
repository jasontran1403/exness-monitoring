package com.foundation.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExnessRepository extends JpaRepository<Exness, Long> {
  Optional<Exness> findByExness(String exness);
}
