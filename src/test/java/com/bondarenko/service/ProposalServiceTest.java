package com.bondarenko.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.Status;
import com.bondarenko.model.User;
import com.bondarenko.util.DaoUtil;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {DBUnitConfig.contextConfigurationLocation})
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class})
public class ProposalServiceTest extends DBUnitConfig {
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private UserService userService;
	@Autowired
	private CarService carService;
	@Autowired
	private StatusService statusService;

	@Test
	public void shouldGetAllProposals() throws Exception {
		int countProposals = 6;
		List<Proposal> proposals = proposalService.getProposals();
		Assert.assertEquals(countProposals, proposals.size());
	}

	@Test
	public void shouldGetProposalById() throws Exception {
		Long proposalId = new Long(1);
		Long userId = new Long(1);
		Long statusId = new Long(1);
		Long carId = new Long(1);
		String description = "description1";
		Timestamp createDate = Timestamp.valueOf("2016-10-1 00:00:00.0");
		Proposal proposal = proposalService.getById(proposalId);
		Assert.assertNotNull(proposal);
		Assert.assertNotNull(proposal.getId());
		Assert.assertNotNull(proposal.getDescription());
		Assert.assertNotNull(proposal.getCar());
		Assert.assertNotNull(proposal.getCreateDate());
		Assert.assertNotNull(proposal.getStatus());
		Assert.assertNotNull(proposal.getUser());
		Assert.assertEquals(userId, proposal.getUser().getId());
		Assert.assertEquals(description, proposal.getDescription());
		Assert.assertEquals(statusId, proposal.getStatus().getId());
		Assert.assertEquals(carId, proposal.getCar().getId());
		Assert.assertEquals(createDate, proposal.getCreateDate());
	}

	@Test
	public void shouldGetProposalsByStatusId() throws Exception {
		Long statusId = new Long(1);
		List<Proposal> proposals = proposalService.getByStatusId(statusId);
		int count = 0;
		for (int i = 0; i < proposals.size(); i++) {
			if (proposals.get(i).getStatus().getId().equals(statusId)) {
				count++;
			}
		}
		Assert.assertEquals(count, proposals.size());
	}

	@Test
	public void shouldGetProposalsByCarId() throws Exception {
		Long carId = new Long(1);
		List<Proposal> proposals = proposalService.getByCarId(carId);
		int count = 0;
		for (int i = 0; i < proposals.size(); i++) {
			if (proposals.get(i).getCar().getId().equals(carId)) {
				count++;
			}
		}
		Assert.assertEquals(count, proposals.size());
	}

	@Test
	public void shouldGetProposalsByUserId() throws Exception {
		Long userId = new Long(1);
		List<Proposal> proposals = proposalService.getByUserId(userId);
		int count = 0;
		for (int i = 0; i < proposals.size(); i++) {
			if (proposals.get(i).getUser().getId().equals(userId)) {
				count++;
			}
		}
		Assert.assertEquals(count, proposals.size());
	}

	@Test
	public void shouldDeleteProposal() throws Exception {
		Long proposalId = new Long(1);
		int countProposals = proposalService.getProposals().size();
		Proposal proposal = proposalService.getById(proposalId);
		Assert.assertNotNull(proposal);
		proposalService.delete(proposal);
		Assert.assertEquals(countProposals - 1, proposalService.getProposals().size());
		Assert.assertNull(proposalService.getById(proposalId));
	}

	@Test
	public void shouldDeleteProposalById() throws Exception {
		Long proposalId = new Long(1);
		int countProposals = proposalService.getProposals().size();
		Assert.assertTrue(proposalService.delete(proposalId));
		Assert.assertEquals(countProposals - 1, proposalService.getProposals().size());
		Assert.assertNull(proposalService.getById(proposalId));
	}

	@SuppressWarnings ("deprecation")
	@Test
	public void shouldSaveProposal() throws Exception {
		int countProposals = proposalService.getProposals().size();
		Long userId = new Long(7);
		Long carId = new Long(3);
		String description = "proposal description";
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		User user = userService.getById(userId);
		Car car = carService.getById(carId);
		Status status = statusService.getByName(DaoUtil.STATUS_OPEN);
		Proposal proposal = new Proposal();
		Assert.assertNotNull(user);
		Assert.assertNotNull(car);
		Assert.assertNotNull(status);
		proposal.setDescription(description);
		proposal.setCar(car);
		proposal.setUser(user);
		proposal = proposalService.save(proposal);
		Assert.assertNotNull(proposal);
		Assert.assertNotNull(proposal.getId());
		Assert.assertNotNull(proposal.getCreateDate());
		Assert.assertEquals(carId, proposal.getCar().getId());
		Assert.assertEquals(userId, proposal.getUser().getId());
		Assert.assertEquals(userId, proposal.getUser().getId());
		Assert.assertEquals(status.getId(), proposal.getStatus().getId());
		Assert.assertEquals(createDate.getDate(), proposal.getCreateDate().getDate());
		Assert.assertEquals(createDate.getMonth(), proposal.getCreateDate().getMonth());
		Assert.assertEquals(createDate.getYear(), proposal.getCreateDate().getYear());
		Assert.assertEquals(createDate.getHours(), proposal.getCreateDate().getHours());
		Assert.assertEquals(createDate.getMinutes(), proposal.getCreateDate().getMinutes());
		Assert.assertEquals(countProposals + 1, proposalService.getProposals().size());
	}

	@Test
	public void shouldUpdateProposal() throws Exception {
		Long userId = new Long(7);
		Long carId = new Long(3);
		Long proposalId = new Long(6);
		String newStatus = "status6";
		String description = "Some new proposal description";
		User user = userService.getById(userId);
		Car car = carService.getById(carId);
		Status status = statusService.getByName(newStatus);
		Proposal proposal = proposalService.getById(proposalId);
		Assert.assertNotNull(user);
		Assert.assertNotNull(car);
		Assert.assertNotNull(status);
		Assert.assertNotNull(proposal);
		proposal.setDescription(description);
		proposal.setCar(car);
		proposal.setUser(user);
		proposal.setStatus(status);
		proposal = proposalService.update(proposal);
		Assert.assertNotNull(proposal);
		Assert.assertEquals(proposalId, proposal.getId());
		Assert.assertEquals(carId, proposal.getCar().getId());
		Assert.assertEquals(userId, proposal.getUser().getId());
		Assert.assertEquals(userId, proposal.getUser().getId());
		Assert.assertEquals(status.getId(), proposal.getStatus().getId());
	}

	@Test
	public void shouldSaveProposalAfterCheckProposalFields() throws Exception {
		Long userId = new Long(7);
		Long carId = new Long(3);
		String description = "proposal description";
		User user = userService.getById(userId);
		Car car = carService.getById(carId);
		Status status = statusService.getByName(DaoUtil.STATUS_OPEN);
		Proposal proposal = new Proposal();
		Assert.assertNotNull(user);
		Assert.assertNotNull(car);
		Assert.assertNotNull(status);
		proposal.setDescription(description);
		Assert.assertNull(proposalService.save(proposal).getId());
		proposal.setCar(car);
		Assert.assertNull(proposalService.save(proposal).getId());
		proposal.setUser(user);
		Assert.assertNotNull(proposalService.save(proposal).getId());
	}

	@Test
	public void shouldSetProposalStatusByProposalIdAndStatusName() throws Exception {
		Long proposalId = new Long(1);
		String newStatusName = "status4";
		Status status = statusService.getByName(newStatusName);
		Proposal proposal = proposalService.getById(proposalId);
		Assert.assertNotNull(status);
		Assert.assertNotNull(proposalService.getById(proposalId));
		Assert.assertNotEquals(newStatusName, proposal.getStatus().getName());
		proposal = proposalService.setStatusByName(proposalId, newStatusName);
		Assert.assertEquals(newStatusName, proposal.getStatus().getName());
	}

	@SuppressWarnings ("deprecation")
	@Test
	public void shouldSaveProposalByUserIdCarIdAndDescription() throws Exception {
		int countProposals = proposalService.getProposals().size();
		Long userId = new Long(7);
		Long carId = new Long(3);
		String description = "proposal description";
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		Status status = statusService.getByName(DaoUtil.STATUS_OPEN);
		Proposal proposal = proposalService.save(userId, carId, description);
		Assert.assertNotNull(proposal);
		Assert.assertNotNull(proposal.getId());
		Assert.assertNotNull(proposal.getCreateDate());
		Assert.assertEquals(carId, proposal.getCar().getId());
		Assert.assertEquals(userId, proposal.getUser().getId());
		Assert.assertEquals(userId, proposal.getUser().getId());
		Assert.assertEquals(status.getId(), proposal.getStatus().getId());
		Assert.assertEquals(createDate.getDate(), proposal.getCreateDate().getDate());
		Assert.assertEquals(createDate.getMonth(), proposal.getCreateDate().getMonth());
		Assert.assertEquals(createDate.getYear(), proposal.getCreateDate().getYear());
		Assert.assertEquals(createDate.getHours(), proposal.getCreateDate().getHours());
		Assert.assertEquals(createDate.getMinutes(), proposal.getCreateDate().getMinutes());
		Assert.assertEquals(countProposals + 1, proposalService.getProposals().size());
	}
}
