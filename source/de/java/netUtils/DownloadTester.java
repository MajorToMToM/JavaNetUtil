package de.java.netUtils;

import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.junit.Test;

import de.java.netUtils.DownloadFactory.PROTOCOL;
import de.java.netUtils.download.HTTPDownload;
import de.java.netUtils.exceptions.download.DownloadAlreadyStartedException;
import de.java.netUtils.exceptions.download.DownloadNotStartedException;
import de.java.netUtils.exceptions.download.NoValidProtocolSpecifiedException;
import de.java.netUtils.exceptions.download.SourceNotAvaibleException;
import de.java.netUtils.handlingSystem.listeners.implementations.IDownloadListener;
import de.java.netUtils.interfaces.IDownload;
import de.java.netUtils.uri.URI_Utils;

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
		IDownload dl = null;

		try {
//			dl = (HTTPDownload) DownloadFactory.getInstance().createDownload("jg-erf.ddns.net/files/ERF_ConfGen.jar",
//					PROTOCOL.HTTP);
		
			dl = DownloadFactory.getInstance().createDownload("http://jg-erf.ddns.net/files/ERF_ConfGen.jar");
			
		} catch (DownloadAlreadyStartedException e1) {
			e1.printStackTrace();
		} catch (SourceNotAvaibleException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (NoValidProtocolSpecifiedException e) {
			e.printStackTrace();
		}
		
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
			}
		});

		try {

//			dl.setURI(URI_Utils.createHTTPURI("jg-erf.ddns.net/files/ERF_ConfGen.jar"));
			dl.startDownload();

			long start = System.currentTimeMillis();
			
			while (!dl.isFinished() && !dl.isDownloadInterrupted()) {
				System.out.println(dl.getDownloadProgess_asString());
			}

			//			try {
			//				System.out.println("DL " + dl.getDownloaded() + " >>> " + new String(dl.getDownloaded()));
			//			} catch (DownloadNotFinishedException e) {
			//				e.printStackTrace();
			//			} catch (DownloadNotStartedException e) {
			//				e.printStackTrace();
			//			}
		} catch (DownloadAlreadyStartedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (SourceNotAvaibleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
}
