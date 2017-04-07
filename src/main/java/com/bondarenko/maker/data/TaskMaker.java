package com.bondarenko.maker.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bondarenko.model.Task;

public class TaskMaker {
	
	public List<Task> generateTasks() {
		String[] variations = new String[]{"Repair the engine.","Dye the hood.","Check the headlights.",
				"Change the wheels.","Fix the door.","Replace the battery.","Make a complete diagnosis.",
				"Preparation for sale.","Preparation for winter.","Trigger the signal.","Check the discs.",
				"Replace the windshield.","Replace the rear window.","Check electrician.","Replace the seat.",
				"Replace the door.","Body trim.","Transmission replacement.","Change of oil.","Engine calibration."};
		List<Task> tasks = new ArrayList<>();
		int numberOfTasks = new Random().nextInt(5) + 1;
		int t1 = 0;
		int t2 = 0;
		int t3 = 0;
		int t4 = 0;
		int t5 = 0;
		while(t1 == t2 || t1==t3|| t1==t4 || t1==t5 ||t2==t5|| t3==t2||t3==t4|| t3==t5 || t4==t2 || t4==t5){
			t1 = new Random().nextInt(variations.length);
			t2 = new Random().nextInt(variations.length);
			t3 = new Random().nextInt(variations.length);
			t4 = new Random().nextInt(variations.length);
			t5 = new Random().nextInt(variations.length);
		}
		String[] descriptions = new String[]{variations[t1],variations[t2],variations[t3],variations[t4],variations[t5]};
		for (int i = 0; i < numberOfTasks; i++) {
			Task task = new Task();
			task.setDescription(descriptions[i]);
			tasks.add(task);
		}
		return tasks;
	}	
	
	private String generatePositiveConclusion() {
		String[] conclusions = new String[]{"Completed.","It's done.","Done.","I've finished.","Like new.","All."};
		return conclusions[new Random().nextInt(conclusions.length)];
	}

	private String generateNegativeConclusion() {
		String[] conclusions = new String[]{"Repair is not possible.","The replacement of another element is required.",
				"Clarification is required.","It takes more time."};
		return conclusions[new Random().nextInt(conclusions.length)];
	}
	
	public String generateConclusion(){
		String conclusion = "";
		switch (new Random().nextInt(2)){
			case 0 : {							
				conclusion = generatePositiveConclusion();
				break;
			}
			case 1 : {
				conclusion = generateNegativeConclusion();
				break;
			}						
		}
		return conclusion;
	}
}
