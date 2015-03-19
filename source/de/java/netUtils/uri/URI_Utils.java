package de.java.netUtils.uri;

import java.net.URI;
import java.net.URISyntaxException;

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
public class URI_Utils {

	/**
	 * The standard constructor of the URI_Utils class
	 */
	private URI_Utils() {
	}

	public static final URI createURI(String protocol, String target) throws URISyntaxException {
		return new URI(protocol + "://" + target);
	}

	public static final URI createFILEURI(String target) throws URISyntaxException {
		return createURI("file", target);
	}
	
	public static final URI createHTTPURI(String target) throws URISyntaxException {
		return createURI("http", target);
	}

	public static final URI createHTTPSURI(String target) throws URISyntaxException {
		return createURI("https", target);
	}
}

