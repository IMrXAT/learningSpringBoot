package com.springbootlearning.ch2;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
    List<VideoEntity> findByName(String name);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String name, String description);

    List<VideoEntity> findByNameContainsIgnoreCase(String name);

    List<VideoEntity> findByDescriptionContainsIgnoreCase(String description);

    List<VideoEntity> findByNameContainsOrDescriptionContainsOrDurationContainsAllIgnoreCase(String name, String description, String duration);

}
