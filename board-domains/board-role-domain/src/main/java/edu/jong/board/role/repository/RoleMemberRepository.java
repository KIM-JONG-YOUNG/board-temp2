package edu.jong.board.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.jong.board.role.entity.RoleMemberEntity;

public interface RoleMemberRepository extends JpaRepository<RoleMemberEntity, RoleMemberEntity.PK>{

}
