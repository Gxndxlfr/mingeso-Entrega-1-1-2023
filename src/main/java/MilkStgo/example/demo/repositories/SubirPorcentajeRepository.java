package MilkStgo.example.demo.repositories;

import MilkStgo.example.demo.entities.SubirPorcentajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubirPorcentajeRepository extends JpaRepository<SubirPorcentajeEntity, Integer> {
}
