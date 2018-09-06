package net.eimarketing.eim_20180528.helper;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ApplicationPathHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPathHelper.class);
	private static final String KEY = "ApplicationPathHelper";
	private static final String SCHEME_END_MARK = "://";
	private static final char END_MARK = '/';

	private ApplicationPathHelper() {
		throw new IllegalAccessError();
	}

	public static String get(HttpServletRequest request) {
		Object obj = request.getAttribute("ApplicationPathHelper");

		if ((obj != null) && ((obj instanceof String))) {
			return (String) obj;
		}

		String path = "";
		try {
			path = request.getContextPath();
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
		}

		String scheme = request.getScheme();
		int port = request.getServerPort();

		boolean noShowPort = (("https".equalsIgnoreCase(scheme)) && (port == 443))
				|| (("http".equalsIgnoreCase(scheme)) && (port == 80));

		String basePath = scheme + "://" + request.getServerName()
				+ (noShowPort ? "" : new StringBuilder(":").append(request.getServerPort()).toString()) + path + '/';

		request.setAttribute("ApplicationPathHelper", basePath);

		return basePath;
	}
}
