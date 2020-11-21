package com.nc.example.repo;

import com.nc.example.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findByUsername(String name);

    Optional<Owner> findById(Long id);
}
