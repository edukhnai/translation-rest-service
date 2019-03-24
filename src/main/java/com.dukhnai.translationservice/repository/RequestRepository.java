package com.dukhnai.translationservice.repository;

import com.dukhnai.translationservice.entity.Request;
import java.util.Optional;

public interface RequestRepository {

    boolean add(Request request);
}
