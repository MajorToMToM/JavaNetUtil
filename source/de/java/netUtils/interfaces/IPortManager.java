package de.java.netUtils.interfaces;

import java.util.Collection;
import java.util.List;

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
public interface IPortManager {

	Collection<IPort> getAllPorts();

	public enum PORT_CREATION {
		EXPLICIT(), FIRSTFREE(), EXPLICIT_LIST(), IMPLICIT();
	}

	/**
	 * 
	 * <hr>
	 *
	 * This method is used to create a new {@link IPort}. 
	 * It is important to check which creationtype is used from the {@link PORT_CREATION} enumeration. 
	 * It is possible that with some {@link PORT_CREATION} types only the first Integer of the portNumbers parameter is used.
	 * <br>
	 *
	 * <hr>
	 * @param creation The creation way.
	 * @param portNumbers The portNumber criteria. It can describe the range or the ports that might be checked if these are avaible.
	 * @return
	 */
	IPort getPort(PORT_CREATION creationType, List<Integer> portNumbers);

}
