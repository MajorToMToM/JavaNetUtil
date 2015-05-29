package de.java.netUtils.serversystems.client.implementations;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import de.java.netUtils.exceptions.server.ConnectionNotEstablishedException;
import de.java.netUtils.serversystems.client.Abstract_Client;

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
public class TCPClient extends Abstract_Client {

	/**
	 * The Logger of the TCPClient Class
	 */
	private static final Logger LOGGER = Logger.getLogger(TCPClient.class.getName());

	private Socket socket;
	private String serverIPAddress;

	/**
	 * The standard constructor of the TCPClient class
	 */
	public TCPClient() {

	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IClient#sendMessage(java.lang.String)
	 */
	@Override
	public void sendMessage(String message) throws ConnectionNotEstablishedException {
		if (socket.isConnected()) {
			try {
				LOGGER.info("Sending Message '" + message + "' to Server ... " + serverIPAddress + ":"
						+ getDestinationPort().getPortNumber());

				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				printWriter.print(message);
				printWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new ConnectionNotEstablishedException();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.ICanCloseConnection#closeConnection()
	 */
	@Override
	public void closeConnection() {
		try {
			socket.close();
			socket = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.ICanOpenConnection#openConnection()
	 */
	@Override
	public void openConnection() throws IOException {
		LOGGER.info("Opening connection to ... " + serverIPAddress + ":" + getDestinationPort().getPortNumber());
		setSocket(new Socket(serverIPAddress, getDestinationPort().getPortNumber(), null, getSourcePort()
				.getPortNumber()));
	}

	/**
	 * <hr>
	 * @return the serverIPAddress
	 * <hr>
	 */
	public final String getServerIPAddress() {
		return serverIPAddress;
	}

	/**
	 * <hr>
	 * @param serverIPAddress 
	 * 		The serverIPAddress - type of String to set.
	 * <hr>
	 */
	public final void setServerIPAddress(String serverIPAddress) {
		this.serverIPAddress = serverIPAddress;
	}

	/**
	 * <hr>
	 * @return the socket
	 * <hr>
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * <hr>
	 * @param socket 
	 * 		The socket - type of Socket to set.
	 * <hr>
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TCPClient [socket=" + socket + ", serverIPAddress=" + serverIPAddress + "]";
	}

}
