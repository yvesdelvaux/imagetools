/**
 * Copyright 2017 - Yves DELVAUX
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under 
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package fr.yvesdelvaux.imagetools.test;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import fr.yvesdelvaux.imagetools.tools.ImageTools;

/**
 * @author Yves DELVAUX
 *
 */
public class ImageToolsTest {
	
	private final static ImageTools imgTools = new ImageTools();
	private final static Logger log = LogManager.getLogger(ImageToolsTest.class);

	/**
	 * Test method for {@link fr.yvesdelvaux.imagetools.tools.ImageTools#resize(java.lang.String, java.lang.String, double)}.
	 */
	@Test
	public void testResize() {
		File fileTest = new File("src/test/resources/IMG_4332.JPG");
		log.debug("Image file test exists : " + fileTest.exists());
		log.debug("File path : " + fileTest.getAbsolutePath());
		// This method test resize and resieNoProp
		imgTools.resize(fileTest, "Test01", 100);
		File resultFile = new File("src/test/resources/resized/Test01.jpg");
		assertTrue("Result file exist",resultFile.exists());
		resultFile.delete();
		resultFile.getParentFile().delete();
	}

}
