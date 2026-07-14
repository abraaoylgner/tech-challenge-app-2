package com.pos.tech.challenge2.app.adapter.out.repository;

import com.pos.tech.challenge2.app.adapter.out.repository.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}