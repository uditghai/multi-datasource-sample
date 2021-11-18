package com.sample.project.sample.data;

import com.sample.project.sample.model.DataSourceModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface DataSourceSelector<E, F> {

    org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataSourceSelector.class);

    List<DataSourceModel<?, ?>> dataSourceEntities = new ArrayList<>();

    default void addDatasource(Datasource<E, F> datasource) {
        addDatasource(datasource, isTrue());
    }

    default void addDatasource(Datasource<E, F> datasource, Predicate<E> predicate) {
        log.info("[addDatasource] New datasource added {} with condition {}", datasource, predicate);
        dataSourceEntities.add(new DataSourceModel<>(datasource, predicate));
    }

    @SuppressWarnings("unchecked")
    default List<Datasource<?, ?>> getDatasources(E data) {
        List<Datasource<?, ?>> response = dataSourceEntities.parallelStream().filter(d -> ((Predicate<E>) d.getPredicate()).test(data))
                .map(DataSourceModel::getDatasource).collect(Collectors.toList());
        log.info("[getDatasources] {} for entity {}", response, data);
        return response;
    }

    default List<Datasource<?, ?>> getDatasources() {
        List<Datasource<?, ?>> response = dataSourceEntities.parallelStream().map(DataSourceModel::getDatasource).collect(Collectors.toList());
        log.info("[getDatasources] {}", response);
        return response;
    }

    default Predicate<E> isTrue() {
        return p -> true;
    }

}