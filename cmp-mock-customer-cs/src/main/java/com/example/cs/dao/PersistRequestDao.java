package com.example.cs.dao;

import java.util.Map;

public interface PersistRequestDao {

    public void persistRequest(Map<String, String> headers, String reqBody);
}
