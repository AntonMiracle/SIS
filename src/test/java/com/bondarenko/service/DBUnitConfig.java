package com.bondarenko.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;

import com.bondarenko.util.ProjectProperties;

public class DBUnitConfig extends DBTestCase {
	public static final String contextConfigurationLocation = "classpath:config/sis-context.xml";
	private static final Logger LOG = LogManager.getLogger();
	private String datasetPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "dataset.xml";
	private ProjectProperties sisPro = ProjectProperties.getInstance();
	protected IDataSet beforeData;
	protected IDatabaseTester tester;

	public DBUnitConfig() {
		super();
		LOG.traceEntry();
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, sisPro.getJdbcDriver());
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, sisPro.getJdbcUrl());
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, sisPro.getJdbcUsername());
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, sisPro.getJdbcPassword());
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, sisPro.getJdbcSchema());
		LOG.traceExit();
	}

	@Before
	public void setUp() {
		LOG.traceEntry();
		try {
			tester = new JdbcDatabaseTester(sisPro.getJdbcDriver(), sisPro.getJdbcUrl(), sisPro.getJdbcUsername(), sisPro.getJdbcPassword());
			beforeData = readXmlDataFile(datasetPath);
			tester.setDataSet(beforeData);
			tester.onSetup();
		} catch (Exception e) {
			LOG.error(e, e);
		}
		LOG.traceExit("Set up complete");
	}

	protected IDataSet readXmlDataFile(String path) {
		LOG.traceEntry();
		FileInputStream input = null;
		IDataSet result = null;
		try {
			input = new FileInputStream(path);
			result = new FlatXmlDataSetBuilder().build(input);
		} catch (DataSetException | FileNotFoundException e) {
			LOG.error(e, e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error(e, e);
				}
			}
		}
		LOG.traceExit();
		return result;
	}

	@After
	public void tearDown() {
		LOG.traceEntry();
		try {
			super.tearDown();
			tester.onTearDown();
		} catch (Exception e) {
			LOG.error(e, e);
		}
		LOG.traceExit();
	}

	@Override
	protected IDataSet getDataSet() {
		return beforeData;
	}

	@Override
	protected DatabaseOperation getSetUpOperation() {
		return DatabaseOperation.REFRESH;
	}

	@Override
	protected DatabaseOperation getTearDownOperation() {
		return DatabaseOperation.DELETE_ALL;
	}

}
