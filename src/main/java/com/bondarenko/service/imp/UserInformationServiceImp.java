package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.UserInformationDao;
import com.bondarenko.model.UserInformation;
import com.bondarenko.service.UserInformationService;

@Service
public class UserInformationServiceImp implements UserInformationService {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private UserInformationDao uiDao;

	@Override
	@Transactional
	public UserInformation save(UserInformation ui) throws RuntimeException {
		try {
			ui.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			return uiDao.save(ui);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public UserInformation update(UserInformation ui) throws RuntimeException {
		try {
			return uiDao.update(ui);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}	

	@Override
	@Transactional
	public Set<UserInformation> getUserInformations() throws RuntimeException {
		try {
			Set<UserInformation> uis = new HashSet<>();
			for (UserInformation ui : uiDao.getAll()) {
				uis.add(ui);
			}
			return uis;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public UserInformation getByPhone(String phone) throws RuntimeException {
		try {
			return uiDao.getByPhone(phone);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public UserInformation getById(Long id) throws RuntimeException {
		try {
			return uiDao.getById(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean isPhoneUnique(String phone) throws RuntimeException {
		try {
			return uiDao.isPhoneUnique(phone);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}
}
