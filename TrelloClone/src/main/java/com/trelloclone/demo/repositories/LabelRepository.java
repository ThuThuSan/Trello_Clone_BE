package com.trelloclone.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trelloclone.demo.models.Label;

public interface LabelRepository extends JpaRepository<Label, Long>{

}
