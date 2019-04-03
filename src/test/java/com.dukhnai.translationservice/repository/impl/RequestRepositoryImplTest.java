package com.dukhnai.translationservice.repository.impl;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.repository.RequestRepository;
import java.util.Date;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
public class RequestRepositoryImplTest {

    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void shouldFindSavedRequestWhenRequestIsPersisted() throws Exception {
        Request originalRequest = new Request(UUID.randomUUID().toString(), new Date(), "cat dog", "en", "ru", "1.2.3.4.5");

        requestRepository.add(originalRequest);

        Request foundRequest = requestRepository.getById(originalRequest.getId()).get();
        assertEquals(originalRequest, foundRequest);
    }
}
