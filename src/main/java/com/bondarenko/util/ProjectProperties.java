package com.bondarenko.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProjectProperties {
	private static final Logger LOG = LogManager.getFormatterLogger();
	public static ProjectProperties projectProps = getInstance();
	private String jdbcUrl;
	private String jdbcDriver;
	private String jdbcUsername;
	private String jdbcPassword;
	private String jdbcPoolSize;
	private String jdbcMaxConn;
	private String jdbcTimeout;
	private String jdbcSchema;
	private String hibernateDialect;
	private String hibernateHBM2DDLAuto;
	private String hibernateShowSQL;

	private ProjectProperties() {
		LOG.traceEntry();
		Properties properties = new Properties();
		InputStream input = null;
		try {			
			input = new FileInputStream("src" + File.separator + "main" + File.separator + "resources" + File.separator + "sis.properties");
			properties.load(input);
			jdbcUrl = properties.getProperty("jdbc.url");
			jdbcDriver = properties.getProperty("jdbc.driver");
			jdbcUsername = properties.getProperty("jdbc.username");
			jdbcPassword = properties.getProperty("jdbc.password");
			jdbcPoolSize = properties.getProperty("jdbc.pool.size");
			jdbcMaxConn = properties.getProperty("jdbc.max.conn");
			jdbcTimeout = properties.getProperty("jdbc.timeout");
			jdbcSchema = properties.getProperty("jdbc.schema");
			hibernateDialect = properties.getProperty("hibernate.dialect");
			hibernateHBM2DDLAuto = properties.getProperty("hibernate.hbm2ddl.auto");
			hibernateShowSQL = properties.getProperty("hibernate.show.sql");			
			LOG.trace("properties are received successfully");
		} catch (IOException ex) {
			LOG.error(ex, ex);
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error(e, e);
					e.printStackTrace();
				}
			}
		}
		LOG.traceExit();
	}

	public static ProjectProperties getInstance() {
		LOG.traceEntry();
		if (projectProps == null) {
			projectProps = new ProjectProperties();
		}
		LOG.traceExit();
		return projectProps;
		
	}

	public static ProjectProperties getProjectProps() {
		return projectProps;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public String getJdbcPoolSize() {
		return jdbcPoolSize;
	}

	public String getJdbcMaxConn() {
		return jdbcMaxConn;
	}

	public String getJdbcTimeout() {
		return jdbcTimeout;
	}

	public String getHibernateDialect() {
		return hibernateDialect;
	}

	public String getHibernateHBM2DDLAuto() {
		return hibernateHBM2DDLAuto;
	}

	public String getHibernateShowSQL() {
		return hibernateShowSQL;
	}

	public String getJdbcSchema() {
		return jdbcSchema;
	}
}
