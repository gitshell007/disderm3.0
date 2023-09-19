package com.disderm.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SSLog {

	private Logger LOG;

	// Enable Logs:
	private static int LOGS_ENABLED = 1;
	private static int LOG_RESPONSES_ENABLED = 1;
	private static int DEV_ENABLED = 0;

	public SSLog() {
		LOG = Logger.getLogger("API.macross");
		LOG.setLevel(Level.INFO);
	}

	public void info (String message) {
		if (LOGS_ENABLED == 1) {
			LOG.info("FEELFARMA " + FFecha.getHoyDDMMAAAAHHMM() + ":" + message);
		}
	}

	public void dev (String message) {
		if (DEV_ENABLED == 1) {
			LOG.info(message);
		}
	}

	public void response (String method, String message) {
		if (LOG_RESPONSES_ENABLED == 1) {
			LOG.info(method + ": " + message);
		}
	}

	public static boolean DevENABLED() {
		if (DEV_ENABLED == 1) {
			return true;
		}
		else
		{
			return false;
		}
	}

}
