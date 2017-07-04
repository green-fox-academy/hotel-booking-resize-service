package com.greenfox.malachit.service;

import com.greenfox.malachit.model.ThumbnailAttributes;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationBuilder {
  public static <T> Specification<ThumbnailAttributes> withParameter(String argument, T t) {
    return ((root, query, cb) -> cb.equal(root.get(argument), t));
  }
}
