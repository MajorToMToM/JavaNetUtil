package de.java.netUtils.interfaces;

import java.net.URI;

import de.java.netUtils.exceptions.download.DownloadAlreadyStartedException;
import de.java.netUtils.exceptions.download.DownloadNotFinishedException;
import de.java.netUtils.exceptions.download.DownloadNotStartedException;
import de.java.netUtils.exceptions.download.SourceNotAvaibleException;
import de.java.netUtils.handlingSystem.listeners.implementations.IDownloadListener;

/**
 * 
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
 * Informations: <br>This interface covers the needed procedures to represent a download of a file from any source.
 *
 * <hr>
 */
public interface IDownload {

	/**
	 * 
	 * <hr>
	 *
	 * With this procedure you can specify the source {@link URI} from which the files shall be downloaded.<br>
	 *
	 * <hr>
	 * @param uri The URI from which data shall be downloaded.
	 */
	public void setURI(URI uri) throws DownloadAlreadyStartedException, SourceNotAvaibleException;

	/**
	 * 
	 * <hr>
	 *
	 * With this procedure you can retrieve the current URI of this {@link IDownload} object.<br>
	 *
	 * <hr>
	 * @return The current {@link URI} or <b>null</b> if no {@link URI} was set yet.
	 */
	public URI getURI();

	/**
	 * 
	 * <hr>
	 *
	 * With this procedure you can receive the downloaded file as a {@link Byte} array. This procedure returns only the data when the download is finished.<br>
	 *
	 * <hr>
	 * @return The downloaded data as a byte array.
	 * 
	 * @throws DownloadNotFinishedException if the {@link IDownload} is not finished yet.
	 * @throws DownloadNotStartedException if the {@link IDownload} has not been started yet.
	 */
	public byte[] getDownloaded() throws DownloadNotFinishedException, DownloadNotStartedException;

	/**
	 * 
	 * <hr>
	 *
	 * With this procedure you can interrupt an already running {@link IDownload}.<br>
	 *
	 * <hr>
	 */
	public void interruptDownload() throws DownloadNotStartedException;

	/**
	 * 
	 * <hr>
	 *
	 * With this method you can launch the download. Before you start it you should use the setter procedures to set the necessary data to this download object.<br>
	 *
	 * <hr>
	 * @throws DownloadAlreadyStartedException When the download was already started this exception is provoked.
	 */
	public void startDownload() throws DownloadAlreadyStartedException;

	/**
	 * 
	 * <hr>
	 *
	 * This procedure is launched when the download is being started.<br>
	 *
	 * <hr>
	 */
	public void onStarted();

	/**
	 * 
	 * <hr>
	 *
	 * This procedure is launched when the download was interrupted.<br>
	 *
	 * <hr>
	 */
	public void onDownloadInterrupted();

	/**
	 * 
	 * <hr>
	 *
	 * This method is launched when the download was finished.<br>
	 *
	 * <hr>
	 */
	public void onFinished();

	/**
	 * 
	 * <hr>
	 *
	 * This method returns the count of {@link Byte}s which needs to be downloaded. This count is the absolute value.<br>
	 *
	 * <hr>
	 * @return The absolute amount of {@link Byte}s to download
	 */
	public long bytesToDownload();

	/**
	 * 
	 * <hr>
	 *
	 * This method returns the amount of already downloaded {@link Byte}s. When the download was not started yet it returns "0".<br>
	 *
	 * <hr>
	 * @return <b>0</b> if the {@link IDownload} was not started yet or the already downloaded {@link Byte}s.
	 */
	public long bytesDownloaded();

	/**
	 * 
	 * <hr>
	 *
	 * This method returns the downloaded amount in a percentage value.<br>
	 *
	 * <hr>
	 * @return The download process as a percentage. <br>100.0 = 100% ; 0.0 = 0%
	 */
	public double downloadPercentDone();

	/**
	 * 
	 * <hr>
	 *
	 * This procedure returns the status of the {@link IDownload}.<br>
	 *
	 * <hr>
	 * @return <b>true</b> only if the {@link IDownload} was ended successfully and <b>false</b> if the download was, interrupted, not launched or did not even started.
	 */
	public boolean isFinished();

	/**
	 * 
	 * <hr>
	 *
	 * This procedure returns the status of the {@link IDownload}.<br>
	 *
	 * <hr>
	 * @return <b>true</b> only if the {@link IDownload} was started in the past and <b>false</b> if it was not started yet.
	 */
	public boolean isStarted();

	/**
	 * 
	 * <hr>
	 *
	 * This procedure returns the status of the {@link IDownload}.<br>
	 *
	 * <hr>
	 * @return <b>true</b> only if the {@link IDownload} was interrupted in any way like, abortion by the user or the unavailability of the source file.
	 */
	public boolean isDownloadInterrupted();

	/**
	 * <hr>
	 *
	 * This method can be used to add a {@link IDownloadListener} to this {@link IDownload} object. 
	 * The {@link IDownloadListener} can be used to react on specific events of the {@link IDownload} object..<br>
	 *
	 * <hr>
	 * @param iDownloadListener The {@link IDownloadListener} to add.
	 */
	public void addDownloadListener(IDownloadListener iDownloadListener);

	/**
	 * <hr>
	 *
	 * This method should be used to display a representation of the {@link IDownload} download progress.<br>
	 *
	 * <hr>
	 * @return
	 */
	public String getDownloadProgess_asString();

}
