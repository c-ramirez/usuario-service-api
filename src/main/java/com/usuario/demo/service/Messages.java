package com.usuario.demo.service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class Messages {
	private static Properties properties = new Properties();
	private static Messages instance;

	private Messages() {
		loadProperties();
	}

	private void loadProperties() {
		try {
			properties.load(Messages.class.getResourceAsStream("/messages.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Messages getInstance() {
		if (instance == null) {
			instance = new Messages();
		}
		return instance;
	}

	public String getMessage(String key, Object... values) {
		return MessageFormat.format(properties.getProperty(key), values);
	}
}
