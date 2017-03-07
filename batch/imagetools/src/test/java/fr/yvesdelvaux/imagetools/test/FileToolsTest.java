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

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import org.junit.Test;

import fr.yvesdelvaux.imagetools.tools.FileTools;

/**
 * @author Yves DELVAUX
 *
 */
public class FileToolsTest {
	
	private static final FileTools fileTools = new FileTools();

	/**
	 * Test method for {@link fr.yvesdelvaux.imagetools.tools.FileTools#readDirectory(java.lang.String)}.
	 */
	@Test
	public void testReadDirectory() {
		List<File> files = fileTools.readDirectory("src/test/resources");
		assertFalse(files.isEmpty());
	}

	/**
	 * Test method for {@link fr.yvesdelvaux.imagetools.tools.FileTools#writeImage(java.lang.String, java.awt.image.BufferedImage)}.
	 */
	@Test
	public void testWriteImage() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR);
		fileTools.writeImage("src/test/resources/testImage.jpg", image);
		File fileImage = new File("src/test/resources/testImage.jpg");
		assertTrue(fileImage.exists());
		fileImage.delete();
	}

	/**
	 * Test method for {@link fr.yvesdelvaux.imagetools.tools.FileTools#createDirectory(java.io.File)}.
	 */
	@Test
	public void testCreateDirectory() {
		File directory = new File("src/test/resources/testdir");
		fileTools.createDirectory(directory);
		assertTrue(directory.exists());
		assertTrue(directory.isDirectory());
		directory.delete();		
	}

}
