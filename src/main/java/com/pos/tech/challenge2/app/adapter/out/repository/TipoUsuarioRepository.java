package com.pos.tech.challenge2.app.adapter.out.repository;
import com.pos.tech.challenge2.app.adapter.out.repository.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {
}