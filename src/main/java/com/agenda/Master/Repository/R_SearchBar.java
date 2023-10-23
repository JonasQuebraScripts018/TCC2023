package com.agenda.Master.Repository;

import com.agenda.Master.Model.M_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface R_SearchBar extends JpaRepository<M_Usuario, Long> {
}
