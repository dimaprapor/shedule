package com.exemple.log.storage;

import java.io.File;

import android.os.Environment;


public class CreateDirIfNotExists {

	public static void CreateDirIfNotExist() {
		File folder = new File(Environment.getExternalStorageDirectory().getPath() + "/AquaReaderLog");
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

}