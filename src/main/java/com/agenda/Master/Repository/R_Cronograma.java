package com.agenda.Master.Repository;

import com.agenda.Master.Model.M_Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Cronograma extends JpaRepository<M_Cronograma, Long> {
    @Query(value = "SELECT * FROM Cronogramas WHERE dataini= :dataini AND datafini= :datafini", nativeQuery = true)
    M_Cronograma BuscarPorCronograma(@Param("dataini") String dataini, @Param("datafini") String datafini);
}
