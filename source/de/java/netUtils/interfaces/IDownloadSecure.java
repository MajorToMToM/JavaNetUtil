package de.java.netUtils.interfaces;

import de.java.netUtils.exceptions.NoSecureModeAvaibleException;

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
public interface IDownloadSecure extends IDownload {

	/**
	 * 
	 * <hr>
	 *
	 * This procedure returns the secure boolean of this {@link IDownload}.<br>
	 *
	 * <hr>
	 * @return <b>true</b> only if this {@link IDownload} is secure.
	 */
	public boolean isSecure();
	
	/**
	 * 
	 * <hr>
	 *
	 * With this method you can set the {@link IDownload} in a secure mode. Some protocols might have no secure mode so this procedure can throw a {@link NoSecureModeAvaibleException}.<br>
	 *
	 * <hr>
	 * @param secure The boolean if the {@link IDownload} should be secure or not.
	 * @throws NoSecureModeAvaibleException If no secure mode is avaible.
	 */
	public void setSecureDownload(boolean secure) throws NoSecureModeAvaibleException;
	
}

