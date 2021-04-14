package com.example.cmp.dao;

import java.util.Map;

public interface PersistRequestDao {

    public void persistRequest(Map<String, String> headers, String reqBody);
}
