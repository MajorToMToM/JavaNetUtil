package de.java.netUtils.serversystems.port;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.logging.Logger;

import de.java.netUtils.interfaces.IPort;

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
public class Port implements IPort {
	/**
	 * The Logger of the Port Class
	 */
	private static final Logger LOGGER = Logger.getLogger(Port.class.getName());
	
	private int portNumber;

	/**
	 * The standard constructor of the Port class
	 */
	public Port() {
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IPort#isAvaible()
	 */
	@Override
	public boolean isAvaible() {
		if (portNumber < MAXPORT && portNumber > MINPORT) {

			ServerSocket ss = null;
			DatagramSocket ds = null;

			try {

				ss = new ServerSocket(portNumber);
				ss.setReuseAddress(true);

				ds = new DatagramSocket(portNumber);
				ds.setReuseAddress(true);
				return true;

			} catch (IOException e) {

			} finally {

				if (ds != null) {
					ds.close();
				}

				if (ss != null) {
					try {
						ss.close();
					} catch (IOException e) {
						/* should not be thrown */
					}
				}

			}
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IPort#getPortNumber()
	 */
	@Override
	public int getPortNumber() {
		return portNumber;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IPort#setPortNumber()
	 */
	@Override
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

}
