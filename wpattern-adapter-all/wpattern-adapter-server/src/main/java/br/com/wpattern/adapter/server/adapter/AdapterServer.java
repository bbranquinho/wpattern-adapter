package br.com.wpattern.adapter.server.adapter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.wpattern.adapter.server.adapter.interfaces.IAdapterServer;
import br.com.wpattern.visitor.utils.interfaces.IElement;
import br.com.wpattern.visitor.utils.interfaces.IProcessor;

@Named
public class AdapterServer implements IAdapterServer, IProcessor {

	private static final Logger logger = Logger.getLogger(AdapterServer.class);

	private static final String HOSTNAME = "localhost";

	private static final int PORT  = 5555;

	private ObjectOutputStream outputStream;

	// Not will be used now.
	@SuppressWarnings("unused")
	private ObjectInputStream inputStream;

	@Override
	public void startAdapter() {
		try {
			Socket s = new Socket(HOSTNAME, PORT);

			this.outputStream = new ObjectOutputStream(s.getOutputStream());
			this.inputStream = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void receiveElement(IElement element) {
		try {
			this.outputStream.writeObject(element);
			this.outputStream.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
