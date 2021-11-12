package com.sample.project.sample.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.sample.project.sample.model.DataSourceModel;

public interface DataSourceSelector<E, F> {

	List<DataSourceModel<?, ?>> dataSourceEntities = new ArrayList<DataSourceModel<?, ?>>();

	default void addDatasource(Datasource<E, F> datasource) {
		addDatasource(datasource, isTrue());
	}

	default void addDatasource(Datasource<E, F> datasource, Predicate<E> predicate) {
		dataSourceEntities.add(new DataSourceModel<E, F>(datasource, predicate));
	}

	@SuppressWarnings("unchecked")
	default List<Datasource<?, ?>> getDatasources(E data) {
		return dataSourceEntities.parallelStream().filter(d -> ((Predicate<E>) d.getPredicate()).test(data))
				.map(d -> d.getDatasource()).collect(Collectors.toList());
	}

	default List<Datasource<?, ?>> getDatasources() {
		return dataSourceEntities.parallelStream().map(d -> d.getDatasource()).collect(Collectors.toList());
	}

	default Predicate<E> isTrue() {
		return p -> true;
	}

}