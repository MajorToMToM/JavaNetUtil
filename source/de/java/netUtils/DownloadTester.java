package de.java.netUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.junit.Test;

import de.java.netUtils.DownloadFactory.PROTOCOL;
import de.java.netUtils.exceptions.download.DownloadAlreadyStartedException;
import de.java.netUtils.exceptions.download.DownloadCanNotBeLaunchedException;
import de.java.netUtils.exceptions.download.DownloadNotFinishedException;
import de.java.netUtils.exceptions.download.DownloadNotStartedException;
import de.java.netUtils.exceptions.download.NoValidProtocolSpecifiedException;
import de.java.netUtils.exceptions.download.SourceNotAvaibleException;
import de.java.netUtils.handlingSystem.listeners.implementations.IDownloadListener;
import de.java.netUtils.interfaces.IDownload;
import de.java.netUtils.utils.Download_Utils;

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
public class DownloadTester {
	/**
	 * The Logger of the DownloadTester Class
	 */
	private static final Logger LOGGER = Logger.getLogger(DownloadTester.class.getName());

	/**
	 * The standard constructor of the DownloadTester class
	 */
	public DownloadTester() {
	}

	@Test
	public void test() {

		try {
//			IDownload dl = DownloadFactory.getInstance().createDownload("jg-erf.ddns.net/index.html", PROTOCOL.HTTP);
			IDownload dl = DownloadFactory.getInstance().createDownload("jg-erf.ddns.net/files/ERF_ConfGen.jar", PROTOCOL.HTTP);
			//			dl = DownloadFactory.getInstance().createDownload("http://jg-erf.ddns.net/files/ERF_ConfGen.jar");

			dl.addDownloadListener(new IDownloadListener() {

				@Override
				public void onStarted() {
					System.out.println("Started!");
				}

				@Override
				public void onInterrupted() {
					System.out.println("Interrupted!");
				}

				@Override
				public void onFinished() {
					System.out.println("Finished!");
					try {
						try {
							System.out.println("Downloaded this :\n"
									+ Download_Utils.retrieveListOfRows(dl.getDownloaded()));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (DownloadNotFinishedException | DownloadNotStartedException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onProgress(double percent) {
					System.out.println(dl.getDownloadProgess_asString());
				}
			});

			try {
				dl.startDownload();

				while (!dl.isFinished() && !dl.isDownloadInterrupted()) {

				}
			} catch (DownloadAlreadyStartedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DownloadCanNotBeLaunchedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SourceNotAvaibleException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			//		} catch (NoValidProtocolSpecifiedException e) {
			//			e.printStackTrace();
		}

		//		try {
		//
		//			
		//
		//			while (!dl.isFinished() && !dl.isDownloadInterrupted()) {
		//				
		//			}
		//
		//		} catch (DownloadAlreadyStartedException e) {
		//			e.printStackTrace();
		//		} catch (DownloadCanNotBeLaunchedException e) {
		//			e.printStackTrace();
		//		}

	}
}
