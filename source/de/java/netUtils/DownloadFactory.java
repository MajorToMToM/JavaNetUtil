package de.java.netUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import de.java.netUtils.download.HTTPDownload;
import de.java.netUtils.download.HTTPSDownload;
import de.java.netUtils.exceptions.download.DownloadAlreadyStartedException;
import de.java.netUtils.exceptions.download.NoValidProtocolSpecifiedException;
import de.java.netUtils.exceptions.download.SourceNotAvaibleException;
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
		HTTP("http"), HTTPS("https");

		private final String shortVal;

		private PROTOCOL(String shortVal) {
			this.shortVal = shortVal;
		}

		/**
		 * <hr>
		 * @return the shortVal
		 * <hr>
		 */
		public String getShortVal() {
			return shortVal;
		}
	}

	/**
	 * The standard constructor of the DownloadFactory class
	 */
	private DownloadFactory() {
	}

	/**
	 * 
	 * <hr>
	 *
	 * This method analyses the {@link String} url if it is a valid url. 
	 * If no proper {@link PROTOCOL} is found the returnd value is null. 
	 * In fact if the download {@link PROTOCOL} is 
	 * known the {@link #createDownload(String, PROTOCOL)} should be used.<br>
	 *
	 * <hr>
	 * @param url The url.
	 * @return Returns a valid {@link IDownload} object or null if the url is not valid.
	 * @throws DownloadAlreadyStartedException
	 * @throws SourceNotAvaibleException
	 * @throws URISyntaxException
	 * @throws NoValidProtocolSpecifiedException 
	 */
	public IDownload createDownload(String url) throws DownloadAlreadyStartedException, SourceNotAvaibleException,
			URISyntaxException, NoValidProtocolSpecifiedException {
		IDownload downloadObject = null;

		if (url.startsWith(PROTOCOL.HTTP.getShortVal() + "://")) {
			downloadObject = new HTTPDownload();
			downloadObject.setURI(new URI(url));
			return downloadObject;
		}

		if (url.startsWith(PROTOCOL.HTTPS.getShortVal() + "://")) {
			downloadObject = new HTTPSDownload();
			downloadObject.setURI(new URI(url));
			return downloadObject;
		}
		
		throw new NoValidProtocolSpecifiedException();

	}

	/**
	 * 
	 * <hr>
	 *
	 * This method creates out of an url and a specific {@link PROTOCOL} a {@link IDownload} object.<br>
	 *
	 * <hr>
	 * @param url
	 * @param protocol
	 * @return
	 * @throws DownloadAlreadyStartedException
	 * @throws SourceNotAvaibleException
	 * @throws URISyntaxException
	 * @throws NoValidProtocolSpecifiedException 
	 */
	public IDownload createDownload(String url, PROTOCOL protocol) throws DownloadAlreadyStartedException,
			SourceNotAvaibleException, URISyntaxException {
		IDownload downloadObject = null;

		if (protocol == PROTOCOL.HTTP) {

			if (url.startsWith(PROTOCOL.HTTP.getShortVal() + "://")) {
				url = url.substring(PROTOCOL.HTTP.getShortVal().length() + "://".length());
			}

			downloadObject = new HTTPDownload();
			downloadObject.setURI(URI_Utils.createHTTPURI(url));
			return downloadObject;
		}

		if (protocol == PROTOCOL.HTTPS) {

			if (url.startsWith(PROTOCOL.HTTP.getShortVal() + "://")) {
				url = url.substring(PROTOCOL.HTTP.getShortVal().length() + "://".length());
			}

			downloadObject = new HTTPSDownload();
			downloadObject.setURI(URI_Utils.createHTTPSURI(url));
			return downloadObject;
		}

		return null;
	}
}
