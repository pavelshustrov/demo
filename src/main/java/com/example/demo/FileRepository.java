package com.example.demo;

import com.example.demo.configs.RepositoryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Repository
public class FileRepository {

    @Autowired
    private RepositoryConfig repositoryConfig;

    private Map<String, String> cache;

    @PostConstruct
    private void init() {
        try {
            List<String> strings = Files.readAllLines(Paths.get(repositoryConfig.getSourceFile()));
            cache = strings.stream().
                    collect(
                            toMap(this::getIdFromEntity,
                                    Function.identity()
                            ));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FileRepository init() failed", e);
        }
    }

    private String getIdFromEntity(final String entity) {
        return entity.substring(0, entity.indexOf(','));
    }

    public String get(String id) {
        return cache.get(id);
    }
}
