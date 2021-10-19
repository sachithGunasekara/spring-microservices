package com.ws.app.mobile.service.impl.utility;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ws.app.mobile.service.utility.UtilityService;

@Service
public class UtilityServiceImpl implements UtilityService {

	@Override
	public String generateUserId() {
		return UUID.randomUUID().toString();
	}

}
