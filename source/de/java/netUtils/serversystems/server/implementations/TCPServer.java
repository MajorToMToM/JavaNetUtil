package de.java.netUtils.serversystems.server.implementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import de.java.netUtils.handlingSystem.event.IEvent;
import de.java.netUtils.handlingSystem.listeners.implementations.IReceivedMessageListener;
import de.java.netUtils.serversystems.client.implementations.TCPClient;
import de.java.netUtils.serversystems.server.Abstract_Server;

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
public class TCPServer extends Abstract_Server<TCPClient> {
	/**
	 * The Logger of the TCPServer Class
	 */
	private static final Logger LOGGER = Logger.getLogger(TCPServer.class.getName());

	private final List<IReceivedMessageListener> messageHandlers = new ArrayList<>();

	private ServerSocket socket;
	private boolean running;
	private final List<TCPClient> clients = new ArrayList<>();

	/**
	 * The standard constructor of the TCPServer class
	 */
	public TCPServer() {
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IServer#addClient(de.java.netUtils.interfaces.IClient)
	 */
	@Override
	public final void addClient(TCPClient client) {
		clients.add(client);
		listenForClientMessage(client);
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IServer#removeClient(de.java.netUtils.interfaces.IClient)
	 */
	@Override
	public final void removeClient(TCPClient client) {
		clients.remove(client);
		client.closeConnection();
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IServer#getClients()
	 */
	@Override
	public Collection<TCPClient> getClients() {
		return clients;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.serversystems.server.Abstract_Server#listenForConnection()
	 */
	@Override
	public void listenForConnection() {
		try {
			LOGGER.info("Listening now for connections ...");
			while (running) {
				LOGGER.info("Listening for client ...");
				TCPClient client = new TCPClient();
				client.setSocket(socket.accept());
				LOGGER.info("Found client!" + client);
				addClient(client);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.ICanReceiveMessages#receiveMessage()
	 */
	@Override
	public void listenForClientMessage(TCPClient client) {
		try {
			LOGGER.info("Listening for Client Message ... ");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getSocket()
					.getInputStream()));
			char[] buffer = new char[200];
			int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
			String nachricht = new String(buffer, 0, anzahlZeichen);

			onMessageReceived(nachricht, client);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.ICanCloseConnection#closeConnection()
	 */
	@Override
	public final void closeConnection() {
		for (TCPClient client : clients) {
			client.closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.serversystems.server.Abstract_Server#closeServer()
	 */
	@Override
	public final void closeServer() {
		try {
			LOGGER.info("Closing Server ...");
			closeConnection();
			socket.close();
			running = false;
			LOGGER.info("Closed Server!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startServer() {
		try {
			LOGGER.info("Starting Server on port " + getSourcePort().getPortNumber());
			socket = new ServerSocket(getSourcePort().getPortNumber());
			running = true;
			
			Thread listener = new Thread() {
				 /* (non-Javadoc)
				 * @see java.lang.Thread#run()
				 */
				@Override
				public void run() {
					listenForConnection();
				}
			};
			listener.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IServer#onMessageReceived()
	 */
	@Override
	public void onMessageReceived(String message, TCPClient byClient) {
		for (IReceivedMessageListener listener : messageHandlers) {
			listener.onMessage(message, byClient);
		}
	};

	public final void addMessageListener(IReceivedMessageListener listener) {
		messageHandlers.add(listener);
	}

}
