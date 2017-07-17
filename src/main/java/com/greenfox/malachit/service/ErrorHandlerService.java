package com.greenfox.malachit.service;

import com.greenfox.malachit.model.ErrorReturnObject;
import com.greenfox.malachit.model.NoImageFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrorHandlerService {

  public Map<String, Object> getResponse(RuntimeException e, String status, String title) {
    Map<String, Object> result = new HashMap();
    List<ErrorReturnObject> errors = new ArrayList<>();

    errors.add(new ErrorReturnObject(status, title, e.getMessage()));
    result.put("errors", errors);
    return result;
  }

}
