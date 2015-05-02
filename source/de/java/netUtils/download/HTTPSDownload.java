package de.java.netUtils.download;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import de.java.netUtils.exceptions.download.DownloadNotStartedException;
import de.java.netUtils.handlingSystem.listeners.implementations.IHasDownloadListeners;
import de.java.netUtils.interfaces.IDownload;

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
public class HTTPSDownload extends HTTPDownload implements IDownload, IHasDownloadListeners {


	/**
	 * <hr>
	 * The constructor of HTTPSDownload.java.
	 * <hr>
	 */
	public HTTPSDownload() {

	}
	
	/* (non-Javadoc)
	 * @see de.java.netUtils.download.HTTPDownload#getDownloaderThread()
	 */
	@Override
	protected Thread getDownloaderThread() {
		return new Thread() {

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				super.run();

				try {

					HttpsURLConnection con = (HttpsURLConnection) getConnection(uri);
					toDownload = getFileSize(con);

					InputStream in = con.getInputStream();
					final ByteArrayOutputStream out = new ByteArrayOutputStream();
					final byte[] buf = new byte[256];

					int n = 0;
					while (-1 != (n = in.read(buf))) {
						currentDownloaded = currentDownloaded + n;
						out.write(buf, 0, n);
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
	
	/* (non-Javadoc)
	 * @see de.java.netUtils.download.HTTPDownload#getConnection(java.net.URI)
	 */
	@Override
	protected HttpURLConnection getConnection(URI uri) throws IOException {
		final URL u = uri.toURL();
		return (HttpsURLConnection) u.openConnection();
	}
	
}
