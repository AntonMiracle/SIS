package com.bondarenko.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bondarenko.maker.data.StatusMaker;
import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.Status;
import com.bondarenko.model.User;

@WebAppConfiguration
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
		Set<Proposal> proposals = proposalService.getProposals();
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
	public void shouldDeleteProposalById() throws Exception {
		Long proposalId = new Long(1);
		int countProposals = proposalService.getProposals().size();
		Proposal proposal = proposalService.getById(proposalId);
		Assert.assertNotNull(proposal);
		proposalService.delete(proposal.getId());
		Assert.assertEquals(countProposals - 1, proposalService.getProposals().size());
		Assert.assertNull(proposalService.getById(proposalId));
	}	

	@SuppressWarnings ("deprecation")
	@Test
	public void shouldSaveProposal() throws Exception {
		int countProposals = proposalService.getProposals().size();
		Long userId = new Long(7);
		Long carId = new Long(3);
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		User user = userService.getById(userId);
		Car car = carService.getById(carId);
		Status status = statusService.getByName(StatusMaker.STATUS_OPEN);
		Proposal proposal = new Proposal();
		Assert.assertNotNull(user);
		Assert.assertNotNull(car);
		Assert.assertNotNull(status);
		proposal.setDescription("proposal description");
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

}
