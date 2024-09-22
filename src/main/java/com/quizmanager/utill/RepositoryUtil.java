package com.quizmanager.utill;

import com.quizmanager.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class RepositoryUtil {
    public static <T, ID> T fetchById(JpaRepository<T, ID> repository, ID id){
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Entity not found with id: " + id)
        );
    }
}
