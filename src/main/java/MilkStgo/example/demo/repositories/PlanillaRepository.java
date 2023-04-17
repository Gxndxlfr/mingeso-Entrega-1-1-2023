package MilkStgo.example.demo.repositories;

import MilkStgo.example.demo.entities.PlanillaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaRepository extends JpaRepository<PlanillaEntity, String> {

}
