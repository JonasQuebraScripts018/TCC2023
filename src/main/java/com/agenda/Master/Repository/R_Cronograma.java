package com.agenda.Master.Repository;

import com.agenda.Master.Model.M_Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface R_Cronograma extends JpaRepository<M_Cronograma, Long> {
    @Query(value = "SELECT * FROM Cronogramas WHERE nome = :nome and id_pessoa = :id_pessoa", nativeQuery = true)
    ArrayList<M_Cronograma> BuscarPorCronograma(@Param("nome") String nome, @Param("id_pessoa") Long id_pessoa);

    @Query(value = "SELECT * FROM Cronogramas WHERE id_pessoa = :id_pessoa", nativeQuery = true)
    ArrayList<M_Cronograma> buscarCronogramaUsuario(@Param("id_pessoa") Long id_pessoa);
}
