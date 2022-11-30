package com.app.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
	
	Optional<T> findById(Long id);
	
	List<T> findAllByIds(List<Long> ids);
	
	T delete(Class<?> clazz, T instance);

	void deleteAll(Class<?> clazz, List<T> instances);
}
