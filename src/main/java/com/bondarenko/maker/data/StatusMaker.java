package com.bondarenko.maker.data;

import com.bondarenko.model.Status;
import com.bondarenko.service.StatusService;

public class StatusMaker {
	public static boolean isInitilized = false;
	public static final String STATUS_OPEN = "open";
	public static final String STATUS_CLOSE = "close";
	public static final String STATUS_ACCEPTED = "accepted";

	public void initialized(StatusService statusService) {
		if (!isInitilized) {
			if (statusService.isNameUnique(STATUS_OPEN)) {
				Status status = new Status();
				status.setName(STATUS_OPEN);
				statusService.save(status);
			}
			if (statusService.isNameUnique(STATUS_CLOSE)) {
				Status status = new Status();
				status.setName(STATUS_CLOSE);
				statusService.save(status);
			}
			if (statusService.isNameUnique(STATUS_ACCEPTED)) {
				Status status = new Status();
				status.setName(STATUS_ACCEPTED);
				statusService.save(status);
			}
			isInitilized = true;
		}
	}
}
