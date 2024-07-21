package com.example.demo.layered.repository;

import com.example.demo.common.redis.support.IdReadModel;
import com.example.demo.layered.entity.MobileOtp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileOptRepository extends CrudRepository<MobileOtp, String> {
    Optional<MobileOtp> findByTel(String tel);
    Optional<IdReadModel<String>> findIdByTel(String id);
}
