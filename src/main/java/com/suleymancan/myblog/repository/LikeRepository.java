package com.suleymancan.myblog.repository;

import com.suleymancan.myblog.model.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface LikeRepository extends CrudRepository<Like,Integer> {

    @Query("Select l from Like l")
    List<Like> findAllLikes();




}
