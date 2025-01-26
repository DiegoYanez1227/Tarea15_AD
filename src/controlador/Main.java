package controlador;

import org.apache.log4j.Logger;


public class Main {
	
	private static Logger log= Logger.getLogger(Main.class);
	public static void main(String[] args) {
		
		log.debug("Log: debug");
		log.info("Log: info");
		log.warn("Log: warning");
		log.error("Log: error");
		log.fatal("Log: fatal");
		
		
	}
}
