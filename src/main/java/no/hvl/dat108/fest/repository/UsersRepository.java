package no.hvl.dat108.fest.repository;


import no.hvl.dat108.fest.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

    Optional<UsersModel>findByMobil(String mobil);
}
