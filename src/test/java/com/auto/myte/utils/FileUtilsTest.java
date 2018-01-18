package com.auto.myte.utils;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.core.util.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileUtilsTest {

	@Test
	public void judeDirExistsTest() {

		File file = new File("C:/test/a.txt");
//    	boolean isDir = FileUtils.judeDirExists(file);
		boolean isDir =FileUtil.createMissingParentDirectories(file);

    	assertEquals(true, isDir);
	}


}
