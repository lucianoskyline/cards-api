package com.cards.repository;

import com.cards.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, BigInteger> {

    Boolean existsByCpf(String cpf);

}
