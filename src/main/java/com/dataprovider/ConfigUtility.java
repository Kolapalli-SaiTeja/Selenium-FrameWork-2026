package com.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtility {

	FileInputStream fis = null;

	public static String readProperty(String keyName) {

		File src = new File(System.getProperty("user.dir") + "/Configuration/config.properties");

		String value = null;

		try {
			FileInputStream fis = new FileInputStream(src);

			Properties pro = new Properties();

			pro.load(fis);

			value = pro.getProperty(keyName);

		} catch (FileNotFoundException e) {

			System.out.println("Unable to locate the file" + e.getMessage());

		}

		catch (IOException e) {

			System.out.println("unable to load the file to properties" + e.getMessage());
		}

		return value;

	}

}
