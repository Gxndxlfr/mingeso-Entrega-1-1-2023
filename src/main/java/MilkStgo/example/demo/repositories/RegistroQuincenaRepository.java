package MilkStgo.example.demo.repositories;

import MilkStgo.example.demo.entities.RegistroQuincenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroQuincenaRepository extends JpaRepository<RegistroQuincenaEntity, String> {

    @Query(value = "select * from registro_quincena as r where r.codigo = :codigo",
            nativeQuery = true)
    RegistroQuincenaEntity getByCodigo(@Param("codigo") String codigo);

    @Query(value = "delete * from registro_quincena as r where r.codigo = :codigo",
            nativeQuery = true)
    void deleteByCodigo(@Param("codigo") String codigo);
}
