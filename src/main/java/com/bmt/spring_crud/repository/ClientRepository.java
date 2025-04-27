package com.bmt.spring_crud.repository;

import com.bmt.spring_crud.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Integer> {

    public Clients findByEmail(String email);
}
