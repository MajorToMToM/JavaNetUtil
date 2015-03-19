package de.java.netUtils.exceptions;

/**
 * <hr>
 * <li><strong>Project   </strong>JavaNetUtil</li>
 * <li><strong>Creater   </strong>Thomas A. Mnich</li>
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
public class NoSecureModeAvaibleException extends Exception {
	/**
	 * <hr>
	 * The field serialVersionUID of type long
	 * <hr>
	 */
	private static final long serialVersionUID = 3397152730381700925L;

	/**
	 * The standard constructor of the NoSecureModeAvaibleException class
	 */
	public NoSecureModeAvaibleException() {
		super("No secure mode is avaible!");
	}
}

