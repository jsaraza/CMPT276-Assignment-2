package com.example.demo.repository;

import com.example.demo.model.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RectangleRepository extends JpaRepository<Rectangle, Long> {
    List<Rectangle> findByName(String name);

    List<Rectangle> findById(int id);

}