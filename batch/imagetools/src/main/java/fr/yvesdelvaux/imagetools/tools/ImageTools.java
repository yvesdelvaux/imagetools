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
package fr.yvesdelvaux.imagetools.tools;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Image tools to work on images.
 * 
 * @author Yves DELVAUX
 *
 */
public class ImageTools {

	/**
	 * Logger {@link Logger}
	 */
	private static final Logger log = LogManager.getLogger(ImageTools.class);

	/**
	 * File tools to manage I/O {@link FileTools}
	 */
	private static final FileTools fileTools = new FileTools();

	/**
	 * Path separator {@link String}
	 */
	private static final String separator;

	/**
	 * Determine separator from the system
	 */
	static {
		if (System.getProperty("os.name").contains("win"))
			separator = "\\";
		else
			separator = "/";
	}

	/**
	 * Resize image from its file, the output name and the size height.
	 * 
	 * @param file
	 *            {@link java.io.File}
	 * @param name
	 *            {@link java.lang.String}
	 * @param sizeHeight
	 *            {@link Integer}
	 * @return {@link BufferedImage}
	 */
	public BufferedImage resize(File file, String name, int sizeHeight) {
		// initialize result variable.
		BufferedImage result = null;

		// Define the output path
		String path = file.getParent() + separator + "resized" + separator + name + ".jpg";

		// Read the image in the file
		BufferedImage inputImage = fileTools.readImage(file);
		
		// calculate the percent of resizing from the scaled height
		double percent = sizeHeight / (double) inputImage.getHeight();
		log.debug("Resize percent : " + percent);
		
		// calculate the scaled width
		int scaledWidth = (int) (inputImage.getWidth() * percent);
		
		// resize the image with all scaled input
		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, sizeHeight, inputImage.getType());
		
		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, sizeHeight, null);
		g2d.dispose();
		
		// Write image
		fileTools.writeImage(path, outputImage);
		
		return result;

	}

}
