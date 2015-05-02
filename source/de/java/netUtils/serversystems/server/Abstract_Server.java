package de.java.netUtils.serversystems.server;

import java.util.logging.Logger;

import de.java.netUtils.interfaces.IClient;
import de.java.netUtils.interfaces.IServer;
import de.java.netUtils.serversystems.Abstract_Connection;

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
public abstract class Abstract_Server<T extends IClient> extends Abstract_Connection implements IServer<T> {

	/**
	 * The Logger of the Abstract_Server Class
	 */
	private static final Logger LOGGER = Logger.getLogger(Abstract_Server.class.getName());

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IServer#startServer()
	 */
	@Override
	public final void startServer() {
		listenForConnection();
	}

	/**
	 * 
	 * <hr>
	 *
	 * This method shuts down the server finally. All connections are closed.<br>
	 *
	 * <hr>
	 */
	public abstract void closeServer();

	/**
	 * 
	 * <hr>
	 *
	 * This method is called after the server is launched. This method should be used to determine if a connection is requested.<br>
	 *
	 * <hr>
	 */
	public abstract void listenForConnection();

}
