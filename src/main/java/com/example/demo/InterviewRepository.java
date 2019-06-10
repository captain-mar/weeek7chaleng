package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface InterviewRepository extends CrudRepository<Interview, Long>{
        Iterable<Interview> findAllByUserId(long id);
}
