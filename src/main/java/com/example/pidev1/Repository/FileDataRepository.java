package com.example.pidev1.Repository;

import com.example.pidev1.Entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository <FileData,Integer> {

    Optional<FileData> findByName(String fileName);

}
