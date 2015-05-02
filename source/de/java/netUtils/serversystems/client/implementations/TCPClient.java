package de.java.netUtils.serversystems.client.implementations;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

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
	public void sendMessage(String message) {
		try {
			OutputStream streamOut = socket.getOutputStream();
			streamOut.write(message.getBytes());
			streamOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.ICanCloseConnection#closeConnection()
	 */
	@Override
	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.ICanOpenConnection#openConnection()
	 */
	@Override
	public void openConnection() {
		try {
			setSocket(new Socket(serverIPAddress, getDestinationPort().getPortNumber(), null, getSourcePort()
					.getPortNumber()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

}
