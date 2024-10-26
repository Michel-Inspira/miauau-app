package com.miauau.platform.repositories;

import com.miauau.platform.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    List<Person> findByIsVolunteerFalse();

    List<Person> findByIsVolunteerTrue();
}
