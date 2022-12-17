package edu.jong.board.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.jong.board.role.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

}
