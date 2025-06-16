package org.example.techmateaccessories.repository;

import org.example.techmateaccessories.domain.Category;
import org.example.techmateaccessories.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
