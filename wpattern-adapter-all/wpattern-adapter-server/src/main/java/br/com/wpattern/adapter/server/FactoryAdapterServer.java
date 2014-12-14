package br.com.wpattern.adapter.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.wpattern.adapter.server.adapter.interfaces.IAdapterServer;
import br.com.wpattern.visitor.server.interfaces.IServerApplication;

public class FactoryAdapterServer {

	private static final String CTX_VISITOR_SERVER = "ctx-wpattern-visitor-server.xml";
	private static final String CTX_ADAPTER_SERVER = "ctx-wpattern-adapter-server.xml";

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(CTX_VISITOR_SERVER, CTX_ADAPTER_SERVER);

		IAdapterServer adapterClient = context.getBean(IAdapterServer.class);
		adapterClient.startAdapter();

		IServerApplication serverApplication = context.getBean(IServerApplication.class);
		serverApplication.startApplication();
	}

}
