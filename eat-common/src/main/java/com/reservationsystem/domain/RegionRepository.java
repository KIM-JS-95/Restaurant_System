package com.reservationsystem.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository {

    List<Region> findAll();
}
