package br.com.wpattern.adapter.client.adapter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.wpattern.adapter.client.adapter.interfaces.IAdapterClient;
import br.com.wpattern.visitor.utils.interfaces.IElement;
import br.com.wpattern.visitor.utils.interfaces.IProcessor;

@Named
public class AdapterClient implements IAdapterClient, Runnable {

	private static final Logger logger = Logger.getLogger(AdapterClient.class);

	@Inject
	private IProcessor processor;

	// Not will be used now.
	@SuppressWarnings("unused")
	private ObjectOutputStream output;

	private ObjectInputStream input;

	private int port = 5555;

	@Override
	public void startAdapter() {
		try {
			Socket s = new ServerSocket(this.port).accept();

			this.output = new ObjectOutputStream(s.getOutputStream());
			this.input = new ObjectInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			this.output = null;
			this.input  = null;
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			this.output = null;
			this.input  = null;
			logger.error(e.getMessage(), e);
		}

		new Thread(this).start();

		logger.info("Client Adapter Started.");
	}

	@Override
	public void run() {
		if (AdapterClient.this.input == null) {
			logger.error("Invalid input stream with null value.");
			System.exit(-1);
			return;
		}

		while (true) {
			try {
				IElement element = (IElement)this.input.readObject();
				this.processor.receiveElement(element);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				break;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
				break;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				break;
			}
		}

	}

}
