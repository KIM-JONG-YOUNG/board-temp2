package edu.jong.board.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.jong.board.role.entity.RoleAuthorityEntity;

public interface RoleAuthorityRepository extends JpaRepository<RoleAuthorityEntity, RoleAuthorityEntity.PK>{

}
