package com.agenda.Master.Repository;

import com.agenda.Master.Model.M_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface R_Usuario extends JpaRepository<M_Usuario, Long> {
    @Query(value = "SELECT * FROM pessoa WHERE cpf= :cpf and senha= :senha", nativeQuery = true)
    M_Usuario BuscarPorCpfSenha(@Param("cpf") Long cpf, @Param("senha") String senha);

    @Query(value = "SELECT * FROM pessoa WHERE nome ILIKE '%'||:nome||'%'", nativeQuery = true)
    ArrayList<M_Usuario> BuscarPorNome(@Param("nome") String nome);
}
