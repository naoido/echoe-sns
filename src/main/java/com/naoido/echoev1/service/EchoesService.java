package com.naoido.echoev1.service;

import com.naoido.echoev1.entity.Echo;
import com.naoido.echoev1.repository.EchoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EchoesService {
    private final EchoesRepository repository;

    @Autowired
    public EchoesService(EchoesRepository repository) {
        this.repository = repository;
    }

    public Page<Echo> findEchoe(Pageable pageable) {
        return this.repository.findAllByDeletedAtIsNullOrderByCreatedAtDesc(pageable);
    }

    public void saveEcho(String userId, String echo) {
        Echo e = new Echo();
        e.setUserId(userId);
        e.setEcho(echo);
        this.repository.save(e);
    }
}
