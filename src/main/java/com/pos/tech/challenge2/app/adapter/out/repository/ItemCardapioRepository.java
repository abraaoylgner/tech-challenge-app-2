package com.pos.tech.challenge2.app.adapter.out.repository;

import com.pos.tech.challenge2.app.adapter.out.repository.entity.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {
    List<ItemCardapioEntity> findByRestauranteId(Long idRestaurante);
}