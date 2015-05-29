package de.java.netUtils.serversystems.port;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import de.java.netUtils.interfaces.IPort;
import de.java.netUtils.interfaces.IPortManager;

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
public class Portmanager implements IPortManager {
	/**
	 * The Logger of the Portmanager Class
	 */
	private static final Logger LOGGER = Logger.getLogger(Portmanager.class.getName());

	private static Portmanager INSTANCE;

	private Map<Integer, IPort> portMap = Collections.synchronizedMap(new HashMap<Integer, IPort>());

	public static final Portmanager getInstance() {
		Portmanager r = INSTANCE;
		if (r == null) {
			synchronized (Portmanager.class) {
				r = INSTANCE;
				if (r == null) {
					r = new Portmanager();
					INSTANCE = r;
				}
			}
		}
		return r;
	}

	/**
	 * The standard constructor of the Portmanager class
	 */
	private Portmanager() {
	}

	/*
	 * (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IPortManager#getPort(de.java.netUtils.interfaces.IPortManager.PORT_CREATION, java.util.List)
	 */
	@Override
	public IPort getPort(PORT_CREATION creation, List<Integer> portNumbers) {
		IPort port = null;

		if (creation == PORT_CREATION.EXPLICIT) {
			int portId = portNumbers.get(0);

			if (isPortAvaible(portId)) {
				if (portMap.containsKey(port)) {
					if (portMap.get(portId).isAvaible()) {
						port = portMap.get(portId);
					}
				} else {
					port = new Port();
					port.setPortNumber(portId);
					portMap.put(portId, port);
				}
			}
		}

		if (creation == PORT_CREATION.FIRSTFREE) {
			
		}

		if (creation == PORT_CREATION.EXPLICIT_LIST) {

		}

		if (creation == PORT_CREATION.IMPLICIT) {

		}

		return port;
	}

	private final boolean isPortAvaible(Integer port) {
		return (IPort.isPortNumberValid(port) );
	}
	
	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IPortManager#getAllPorts()
	 */
	@Override
	public Collection<IPort> getAllPorts() {
		return portMap.values();
	}
}
