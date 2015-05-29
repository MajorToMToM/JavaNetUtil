package de.java.netUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import de.java.netUtils.exceptions.server.ConnectionNotEstablishedException;
import de.java.netUtils.handlingSystem.listeners.implementations.IReceivedMessageListener;
import de.java.netUtils.interfaces.IPort;
import de.java.netUtils.interfaces.IPortManager.PORT_CREATION;
import de.java.netUtils.serversystems.client.implementations.TCPClient;
import de.java.netUtils.serversystems.port.Portmanager;
import de.java.netUtils.serversystems.server.implementations.TCPServer;

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

		/*
		 * SERVER
		 */

		TCPServer server = new TCPServer();

		List<Integer> serverPorts = new ArrayList<>();
		serverPorts.add(1337);

		server.setSourcePort(Portmanager.getInstance().getPort(PORT_CREATION.EXPLICIT, serverPorts));
		server.startServer();

		server.addMessageListener(new IReceivedMessageListener() {

			@Override
			public void onMessage(String message, Object source) {
				LOGGER.info("Received Message: '" + message + "' from " + source);
			}
		});
		
		LOGGER.info("Started Server...");

		/*
		 * CLIENT
		 */

		List<Integer> clientPorts = new ArrayList<>();
		clientPorts.add(1338);

		TCPClient client = new TCPClient();
		client.setServerIPAddress("127.0.0.1");
//		client.setServerIPAddress("JG-ERF.DDNS.NET");

		client.setSourcePort(Portmanager.getInstance().getPort(PORT_CREATION.EXPLICIT, clientPorts));
		client.setDestinationPort(server.getSourcePort());

		try {
			client.openConnection();
			try {
				client.sendMessage("Hi");
			} catch (ConnectionNotEstablishedException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		

		//		server.closeServer();
		//		client.closeConnection();

	}
}
