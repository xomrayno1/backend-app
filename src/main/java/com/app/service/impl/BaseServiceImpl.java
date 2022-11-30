package com.app.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.enumtype.Status;
import com.app.service.BaseService;
 
public class BaseServiceImpl<T> implements BaseService<T>{
	
	private final JpaRepository<T, Long> jpaRepository;
	 
	public BaseServiceImpl(JpaRepository<T, Long> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}
 
	public Optional<T> findById(Long id) {
		return jpaRepository.findById(id);
	}

	@Override
	public List<T> findAllByIds(List<Long> ids) {
		return jpaRepository.findAllById(ids);
	}

	@Override
	public T delete(Class<?> clazz, T instance) {
		try {
			Method setStatusMethod = clazz.getMethod("setStatus", int.class);
			if(setStatusMethod != null) {
				setStatusMethod.invoke(instance, Status.IN_ACTIVE.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jpaRepository.save(instance);
	}

	@Override
	public void deleteAll(Class<?> clazz, List<T> instances) {
		try {
			Method setStatusMethod = clazz.getMethod("setStatus", int.class);
			if(setStatusMethod != null) {
				instances.forEach(instance -> {
					try {
						setStatusMethod.invoke(instance, Status.IN_ACTIVE.getValue());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jpaRepository.saveAll(instances);
	}
 
}
