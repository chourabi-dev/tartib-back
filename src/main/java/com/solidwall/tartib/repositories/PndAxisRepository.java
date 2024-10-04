package com.solidwall.tartib.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.solidwall.tartib.entities.PndAxisEntity;

@Repository
public interface PndAxisRepository extends JpaRepository<PndAxisEntity, Long> {

    Optional<PndAxisEntity> findByName(String name);

}
