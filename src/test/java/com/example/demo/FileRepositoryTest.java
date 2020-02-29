package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileRepositoryTest {

    @Autowired
    FileRepository fileRepository;

    @Test
    public void entityRetrieve() {
        String s = fileRepository.get("1");
        Assert.assertTrue(s.contains("Goroka"));
    }

    @Test
    public void emptyEntityRetrieve() {
        Assert.assertNull(fileRepository.get(null));
    }
}
