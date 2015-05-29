package de.java.netUtils.uri;

import java.net.URI;
import java.net.URISyntaxException;

import de.java.netUtils.DownloadFactory;

/**
 * <hr>
 * <li><strong>Project </strong>JavaNetUtil</li>
 * <li><strong>Creater </strong>Thomas Andreas Mnich</li>
 * <hr>
 * <br>
 * <center> <a rel="license"
 * href="http://creativecommons.org/licenses/by-sa/4.0/"><img
 * alt="Creative Commons Lizenzvertrag" style="border-width:0"
 * src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />
 * <span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">JavaNetUtils
 * </span> von <a xmlns:cc="http://creativecommons.org/ns#"
 * href="https://github.com/MajorToMToM/JavaNetUtil"
 * property="cc:attributionName" rel="cc:attributionURL">MajorToMToM / Thomas A.
 * Mnich</a> ist lizenziert unter einer <a rel="license"
 * href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons
 * Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International
 * Lizenz</a>. </center> <br>
 *
 * <hr>
 *
 * Informations: <br>
 *
 * <hr>
 */
public class URI_Utils {

	public final static String PROT_SUFFIX = "://";

	/**
	 * The standard constructor of the URI_Utils class
	 */
	private URI_Utils() {
	}

	/**
	 * 
	 * <hr>
	 *
	 * Create an {@link URI} object with a specific protocol and a given String.<br>
	 *
	 * <hr>
	 * @param protocol
	 * @param target
	 * @return
	 * @throws URISyntaxException
	 */
	public static final URI createURI(String protocol, String target) throws URISyntaxException {
		return new URI(protocol + PROT_SUFFIX + target);
	}

	/**
	 * 
	 * <hr>
	 *
	 * This creates an {@link URI} with "file" protocol.<br>
	 *
	 * <hr>
	 * @param target
	 * @return
	 * @throws URISyntaxException
	 */
	public static final URI createFILEURI(String target) throws URISyntaxException {
		return createURI(DownloadFactory.PROTOCOL.FILE.getShortVal(), target);
	}

	/**
	 * 
	 * <hr>
	 *
	 * This creates an {@link URI} with "http" protocol.<br>
	 *
	 * <hr>
	 * @param target
	 * @return
	 * @throws URISyntaxException
	 */
	public static final URI createHTTPURI(String target) throws URISyntaxException {
		return createURI(DownloadFactory.PROTOCOL.HTTP.getShortVal(), target);
	}

	/**
	 * 
	 * <hr>
	 *
	 * This creates an {@link URI} with "https" protocol.<br>
	 *
	 * <hr>
	 * @param target
	 * @return
	 * @throws URISyntaxException
	 */
	public static final URI createHTTPSURI(String target) throws URISyntaxException {
		return createURI(DownloadFactory.PROTOCOL.HTTPS.getShortVal(), target);
	}
}
