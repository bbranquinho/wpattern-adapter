package br.com.wpattern.adapter.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.wpattern.adapter.client.adapter.interfaces.IAdapterClient;
import br.com.wpattern.visitor.client.interfaces.IClientApplication;

public class FactoryAdapterClient {

	private static final String CTX_VISITOR_CLIENT = "ctx-wpattern-visitor-client.xml";
	private static final String CTX_VISITOR_SERVER = "ctx-wpattern-adapter-client.xml";

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(CTX_VISITOR_CLIENT, CTX_VISITOR_SERVER);

		IClientApplication clientApplication = context.getBean(IClientApplication.class);
		clientApplication.startApplication();

		IAdapterClient adapterClient = context.getBean(IAdapterClient.class);
		adapterClient.startAdapter();
	}

}
