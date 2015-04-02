package de.java.netUtils.serversystems;

import java.util.logging.Logger;

import de.java.netUtils.interfaces.IConnection;
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
public abstract class Abstract_Connection implements IConnection {

	private IPort destination;
	private IPort source;

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IConnection#getDestinationPort()
	 */
	@Override
	public IPort getDestinationPort() {
		return destination;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IConnection#setDestinationPort(de.java.netUtils.interfaces.IPort)
	 */
	@Override
	public void setDestinationPort(IPort port) {
		destination = port;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IConnection#getSourcePort()
	 */
	@Override
	public IPort getSourcePort() {
		return source;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IConnection#setSourcePort(de.java.netUtils.interfaces.IPort)
	 */
	@Override
	public void setSourcePort(IPort port) {
		source = port;
	}

}
