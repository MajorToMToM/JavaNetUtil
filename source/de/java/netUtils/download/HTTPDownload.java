package de.java.netUtils.download;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.java.netUtils.exceptions.download.DownloadAlreadyStartedException;
import de.java.netUtils.exceptions.download.DownloadCanNotBeLaunchedException;
import de.java.netUtils.exceptions.download.DownloadNotFinishedException;
import de.java.netUtils.exceptions.download.DownloadNotStartedException;
import de.java.netUtils.exceptions.download.SourceNotAvaibleException;
import de.java.netUtils.handlingSystem.listeners.implementations.IDownloadListener;
import de.java.netUtils.handlingSystem.listeners.implementations.IHasDownloadListeners;
import de.java.netUtils.interfaces.IDownload;
import de.java.netUtils.utils.Math_Utils;

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
public class HTTPDownload implements IDownload, IHasDownloadListeners {

	protected final List<IDownloadListener> listeners = new ArrayList<>();

	protected Thread downloader;

	protected byte[] downloaded;

	protected long currentDownloaded = 0;
	protected long toDownload = 0;

	protected URI uri;

	protected long download_Started_millis;
	protected long download_Finished_millis;

	protected boolean started = false, finished = false, interrupted = false, secure = false, canLaunch = false;

	/**
	 * <hr>
	 * The constructor of HTTPDownload.java.
	 * <hr>
	 */
	public HTTPDownload() {

	}

	protected Thread getDownloaderThread() {
		return new Thread() {

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				super.run();

				try {

					HttpURLConnection con = getConnection(uri);
					toDownload = getFileSize(con);

					InputStream in = con.getInputStream();
					final ByteArrayOutputStream out = new ByteArrayOutputStream();
					final byte[] buf = new byte[256];

					int n = 0;
					while (-1 != (n = in.read(buf))) {
						currentDownloaded = currentDownloaded + n;
						out.write(buf, 0, n);
						
						for (IDownloadListener listener : listeners) {
							listener.onProgress(downloadPercentDone());
						}
					}

					out.close();
					in.close();
					con.disconnect();

					downloaded = out.toByteArray();
					finished = true;

					download_Finished_millis = System.currentTimeMillis();

					onFinished();

				} catch (IOException e1) {

					try {
						interruptDownload();
					} catch (DownloadNotStartedException e) {
						e.printStackTrace();
					}

					e1.printStackTrace();

				}

			}

		};
	}

	/**
	 * Gets file size using an HTTP connection with GET method
	 *
	 * @return file size in bytes
	 * @throws IOException
	 */
	protected final long getFileSize(HttpURLConnection con) throws IOException {
		try {
			return Long.parseLong(con.getHeaderField("Content-Length"));
		} catch (NumberFormatException nfe) {
		}
		return 0;
	}

	protected HttpURLConnection getConnection(URI uri) throws IOException {
		final URL u = uri.toURL();
		return (HttpURLConnection) u.openConnection();
	}

	protected final boolean isAvaible(URI uri) throws IOException {
		HttpURLConnection con = getConnection(uri);

		if (con == null) {
			return false;
		}

		con.setRequestMethod("HEAD");
		con.connect();

		final InputStream os = con.getInputStream();
		final int response = con.getResponseCode();

		os.close();
		con.disconnect();

		return (response == 200) ? true : false;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#setURI(java.net.URI)
	 */
	@Override
	public void setURI(URI uri) throws DownloadAlreadyStartedException, SourceNotAvaibleException {
		if (started) {
			throw new DownloadAlreadyStartedException();
		} else {

			if (uri == null) {
				canLaunch = false;
				this.uri = uri;
			} else {
				try {
					if (isAvaible(uri)) {
						canLaunch = true;
						this.uri = uri;
					} else {
						canLaunch = false;
						throw new SourceNotAvaibleException();
					}
				} catch (IOException e) {
					//					e.printStackTrace();
					canLaunch = false;
					throw new SourceNotAvaibleException();
				}
			}

		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#getURI()
	 */
	@Override
	public URI getURI() {
		return uri;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#getDownloaded()
	 */
	@Override
	public byte[] getDownloaded() throws DownloadNotFinishedException, DownloadNotStartedException {
		if (!started) {
			throw new DownloadNotStartedException();
		} else {
			if (!finished) {
				throw new DownloadNotFinishedException();
			} else {
				return downloaded;
			}
		}

	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#interruptDownload()
	 */
	@Override
	public void interruptDownload() throws DownloadNotStartedException {
		interrupted = true;
		onDownloadInterrupted();
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#startDownload()
	 */
	@Override
	public void startDownload() throws DownloadAlreadyStartedException, DownloadCanNotBeLaunchedException {
		if (!started) {

			if (canLaunch) {
				started = true;
				onStarted();
				download_Started_millis = System.currentTimeMillis();
				downloader = getDownloaderThread();
				downloader.start();
			} else {
				throw new DownloadCanNotBeLaunchedException();
			}

		} else {
			throw new DownloadAlreadyStartedException();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#onStarted()
	 */
	@Override
	public void onStarted() {
		for (IDownloadListener listener : listeners) {
			listener.onStarted();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#onDownloadInterrupted()
	 */
	@Override
	public void onDownloadInterrupted() {
		for (IDownloadListener listener : listeners) {
			listener.onInterrupted();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#onFinished()
	 */
	@Override
	public void onFinished() {
		for (IDownloadListener listener : listeners) {
			listener.onFinished();
		}
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#bytesToDownload()
	 */
	@Override
	public long bytesToDownload() {
		return toDownload;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#bytesDownloaded()
	 */
	@Override
	public long bytesDownloaded() {
		return currentDownloaded;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#downloadPercentDone()
	 */
	@Override
	public double downloadPercentDone() {
		return Math_Utils.calcPercent(toDownload, currentDownloaded);
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#isFinished()
	 */
	@Override
	public boolean isFinished() {
		return finished;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#isStarted()
	 */
	@Override
	public boolean isStarted() {
		return started;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#isDownloadInterrupted()
	 */
	@Override
	public boolean isDownloadInterrupted() {
		return interrupted;
	}

	/* (non-Javadoc)
	 * @see de.java.netUtils.handlingSystem.listeners.implementations.IHasDownloadListeners#addDownloadListener(de.java.netUtils.handlingSystem.listeners.implementations.IDownloadListener)
	 */
	@Override
	public void addDownloadListener(IDownloadListener listener) {
		listeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see de.java.netUtils.interfaces.IDownload#getDownloadProgess_asString()
	 */
	@Override
	public String getDownloadProgess_asString() {
		return "HTTPDownload [ " + uri + " | Running: " + (System.currentTimeMillis() - download_Started_millis)
				+ " milliseconds ] " + bytesDownloaded() + " of " + bytesToDownload() + " Bytes ("
				+ downloadPercentDone() + " %) downloaded.";
	}
}
