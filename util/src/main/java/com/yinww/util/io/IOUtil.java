package com.yinww.util.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil {
	
	private static final Logger log = LoggerFactory.getLogger(IOUtil.class);
	
	public static void close(OutputStream os) {
		if(os != null) {
			try {
				os.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	public static void close(InputStream is) {
		if(is != null) {
			try {
				is.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	public static void close(BufferedReader reader) {
		if(reader != null) {
			try {
				reader.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}

}
