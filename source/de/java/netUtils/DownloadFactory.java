package de.java.netUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import de.java.netUtils.download.HTTPDownload;
import de.java.netUtils.download.HTTPSDownload;
import de.java.netUtils.exceptions.DownloadAlreadyStartedException;
import de.java.netUtils.exceptions.SourceNotAvaibleException;
import de.java.netUtils.interfaces.IDownload;
import de.java.netUtils.uri.URI_Utils;

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
public class DownloadFactory {
	/**
	 * The Logger of the DownloadFactory Class
	 */
	private static final Logger LOGGER = Logger.getLogger(DownloadFactory.class.getName());

	private static DownloadFactory INSTANCE;

	public static synchronized DownloadFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DownloadFactory();
		}
		return INSTANCE;
	}

	public enum PROTOCOL {
		HTTP(), HTTPS();

		private PROTOCOL() {

		}
	}

	/**
	 * The standard constructor of the DownloadFactory class
	 */
	private DownloadFactory() {
	}

	public IDownload createDownload(String url, PROTOCOL protocol) throws DownloadAlreadyStartedException,
			SourceNotAvaibleException, URISyntaxException {
		IDownload downloadObject = null;
		
		if (protocol == PROTOCOL.HTTP) {
			downloadObject = new HTTPDownload();
			downloadObject.setURI(URI_Utils.createHTTPURI(url));
		}
		
		if (protocol == PROTOCOL.HTTPS) {
			downloadObject = new HTTPSDownload();
			downloadObject.setURI(URI_Utils.createHTTPSURI(url));
		}
		
		
		return downloadObject;
	}
}
