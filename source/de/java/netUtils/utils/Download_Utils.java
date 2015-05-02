package de.java.netUtils.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <hr>
 * <li><strong>Project   </strong>JavaNetUtil</li>
 * <li><strong>Creater   </strong>Thomas Andreas Mnich</li>
 * <hr>
 * <br>
 * <center>
 * <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">JavaNetUtils </span> von <a xmlns:cc="http://creativecommons.org/ns#" href="https://github.com/MajorToMToM/JavaNetUtil" property="cc:attributionName" rel="cc:attributionURL">MajorToMToM / Thomas A. Mnich</a> ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.
 * </center>
 * <br>
 *
 * <hr>
 *
 * Informations: <br>
 *
 * <hr>
 */
public class Download_Utils {
	/**
	 * The Logger of the DownloadUtils Class
	 */
	private static final Logger LOGGER = Logger.getLogger(Download_Utils.class.getName());

	/**
	 * The standard constructor of the DownloadUtils class
	 */
	public Download_Utils() {
	}

	/**
	 * 
	 * <hr>
	 *
	 * This method is used to retrieve a list of String from any {@link Byte} array. <br>
	 *
	 * <hr>
	 * @param byteArray The byte array which should be converted to a {@link List} of {@link String}s.
	 * @return
	 * @throws IOException
	 */
	public static final List<String> retrieveListOfRows(byte[] byteArray) throws IOException {
		List<String> result = new ArrayList<>();

		// create the streams
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
		final InputStreamReader isr = new InputStreamReader(bis);
		final BufferedReader br = new BufferedReader(isr);

		// read until all lines are read.
		boolean searching = true;
		while (searching) {
			String line = br.readLine();
			if (line != null) {
				result.add(line);
			} else {
				searching = false;
			}
		}

		return result;
	}
}
