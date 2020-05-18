package com.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class downloadPng {

	public static void main(String[] args) {

		try {
			URL url = new URL(
					"http://eip.hand-china.com/o/com.hand.hbp.mypersonalcourse/images/arrow.png");
			File dir =  new File("src/com/Test/arrow.png");
			FileUtils.copyURLToFile(url,dir);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
