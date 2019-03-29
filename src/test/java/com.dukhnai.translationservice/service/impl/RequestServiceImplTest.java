package com.dukhnai.translationservice.service.impl;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.exception.RequestDoesNotPresentException;
import com.dukhnai.translationservice.service.RequestService;
import java.util.Date;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestServiceImplTest {

    @Autowired
    private RequestService requestService;

    @Test
    public void shouldFindRequestWhenRequestIsAlreadyStored() throws Exception {
        String id = UUID.randomUUID().toString();
        Date date = new Date();
        String text = "cat dog";
        String fromLanguage = "en";
        String toLanguage = "ru";
        String clientIp = "1.2.3.4.5";
        Request originalRequest = new Request(id, date, text, fromLanguage, toLanguage, clientIp);
        requestService.saveRequestData(id, date, text, fromLanguage, toLanguage, clientIp);

        Request foundRequest = requestService.getById(originalRequest.getId());

        Assert.assertEquals(originalRequest, foundRequest);
    }

    @Test(expected = RequestDoesNotPresentException.class)
    public void shouldThrowWhenRequestIsNotStored() throws RequestDoesNotPresentException {
        String notExistingId = UUID.randomUUID().toString();

        Request foundRequest = requestService.getById(notExistingId);
    }
}
