package de.java.netUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
public class ServerFactory {

	private static final ExecutorService pool = Executors.newCachedThreadPool();

	private static ServerFactory INSTANCE;

	/**
	 * <hr>
	 * @return the iNSTANCE
	 * <hr>
	 */
	public synchronized static ServerFactory getINSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new ServerFactory();
		}
		return INSTANCE;
	}

	/**
	 * The Logger of the ServerFactory Class
	 */
	private static final Logger LOGGER = Logger.getLogger(ServerFactory.class.getName());

	/**
	 * The standard constructor of the ServerFactory class
	 */
	public ServerFactory() {
	}
}
