package com.bondarenko.maker.data;

import java.util.Random;

import com.bondarenko.model.Proposal;

public class ProposalMaker {

	public Proposal[] generateProposals(int quantity){
		Proposal[] props = new Proposal[quantity];
		for(int i = 0; i < props.length; i++){
			Proposal prop = new Proposal();
			prop.setDescription(generateDescription());
			props[i] = prop;
		}
		return props;
	}
	
	private String generateDescription() {
		String[] colors = new String[]{"red","green","blue","white","black","silver","gold","bronze"};
		return "Color " + colors[new Random().nextInt(colors.length)];
	}
}
