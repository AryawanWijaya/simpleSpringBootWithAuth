package com.learning.spring.boot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {//yang pertama adalah tipe kelasnya, yang kedua tipe data idnya

}
