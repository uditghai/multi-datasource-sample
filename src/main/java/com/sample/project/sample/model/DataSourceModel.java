package com.sample.project.sample.model;

import java.util.function.Predicate;

import com.sample.project.sample.data.Datasource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataSourceModel<E, F> {
	private Datasource<E, F> datasource;
	private Predicate<E> predicate;
}