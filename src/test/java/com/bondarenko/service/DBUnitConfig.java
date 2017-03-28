package com.bondarenko.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

import com.bondarenko.util.ProjectProperties;

public class DBUnitConfig extends DBTestCase {
	public static final String contextConfigurationLocation = "classpath:config/sis-context.xml";
	private String datasetPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "dataset.xml";
	private ProjectProperties sisPro = ProjectProperties.getInstance();
	protected IDataSet beforeData;
	protected IDatabaseTester tester;

//	public DBUnitConfig() {
//		super();
//		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, sisPro.getJdbcDriver());
//		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, sisPro.getJdbcUrl());
//		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, sisPro.getJdbcUsername());
//		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, sisPro.getJdbcPassword());
//		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, sisPro.getJdbcSchema());
//	}

	@Before
	public void setUp() throws Exception {
		tester = new JdbcDatabaseTester(sisPro.getJdbcDriver(), sisPro.getJdbcUrl(), sisPro.getJdbcUsername(),sisPro.getJdbcPassword());
		beforeData = readXmlDataFile(datasetPath);
		tester.setDataSet(beforeData);
		tester.onSetup();
	}

	protected IDataSet readXmlDataFile(String path) throws Exception {
		FileInputStream input = null;
		IDataSet result = null;
		try {
			input = new FileInputStream(path);
			result = new FlatXmlDataSetBuilder().build(input);
		} catch (DataSetException | FileNotFoundException e) {
			throw new Exception(e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					throw new Exception(e);
				}
			}
		}
		return result;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return beforeData;
	}

	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {		
		return DatabaseOperation.DELETE_ALL;
	}

}
