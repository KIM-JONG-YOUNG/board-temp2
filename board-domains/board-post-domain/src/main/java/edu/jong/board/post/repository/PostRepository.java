package edu.jong.board.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.jong.board.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
