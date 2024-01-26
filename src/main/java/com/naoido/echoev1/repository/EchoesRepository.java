package com.naoido.echoev1.repository;

import com.naoido.echoev1.entity.Echo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EchoesRepository extends JpaRepository<Echo, Integer> {
    Page<Echo> findAllByDeletedAtIsNullOrderByCreatedAtDesc(Pageable pageable);
}
