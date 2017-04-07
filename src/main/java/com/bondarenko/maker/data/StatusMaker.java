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
				statusService.save(STATUS_OPEN);
			}
			if (statusService.isNameUnique(STATUS_CLOSE)) {
				statusService.save(STATUS_CLOSE);
			}
			if (statusService.isNameUnique(STATUS_ACCEPTED)) {
				statusService.save(STATUS_ACCEPTED);
			}
			isInitilized = true;
		}
	}

	public Status generateOpen(StatusService statusService) {
		return statusService.getByName(STATUS_OPEN);
	}

	public Status generateClose(StatusService statusService) {
		return statusService.getByName(STATUS_CLOSE);
	}

	public Status generateAccepted(StatusService statusService) {
		return statusService.getByName(STATUS_ACCEPTED);
	}
}
