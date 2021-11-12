package com.sample.project.sample.data;

import java.util.List;

public interface Datasource<E, F> {

	F create(E request);

	F read(String id);

	List<F> read();

	F update(String id, E request);

	void delete(String id);

}
