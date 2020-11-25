package com.nc.example.repo;

import com.nc.example.model.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    Page<Cat> findAll(Pageable pageable);

    Optional<Cat> findById(Long id);

    List<Cat> findAllByGender(String gender);

    List<Cat> findAllByCatFatherIdOrCatMotherId(Long fatherId, Long motherId);

}
