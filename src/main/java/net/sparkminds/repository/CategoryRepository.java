package net.sparkminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
