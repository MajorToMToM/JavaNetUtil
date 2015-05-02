package de.java.netUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import de.java.netUtils.DownloadFactory.PROTOCOL;
import de.java.netUtils.download.HTTPDownload;
import de.java.netUtils.exceptions.download.DownloadAlreadyStartedException;
import de.java.netUtils.exceptions.download.SourceNotAvaibleException;
import de.java.netUtils.handlingSystem.listeners.implementations.IDownloadListener;
import de.java.netUtils.interfaces.IConnection;
import de.java.netUtils.interfaces.IPort;
import de.java.netUtils.interfaces.IPortManager;
import de.java.netUtils.serversystems.server.implementations.TCPServer;
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
public class ServerTester {
	/**
	 * The Logger of the ServerTester Class
	 */
	private static final Logger LOGGER = Logger.getLogger(ServerTester.class.getName());

	/**
	 * <hr>
	 * The constructor of ServerTester.java.
	 * <hr>
	 */
	public ServerTester() {
		
	}
	
	@Test
	public void test() {
		TCPServer server = new TCPServer();
	}
}

