package com.tineo.wallet_backend.repository;

import com.tineo.wallet_backend.entity.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Long, CardModel> {
}
