package leod7k.quizmica.servidor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JTextArea;

public class ProvaListener {
	
	private HashMap<ClientInfo, Map<Integer, String>> mapaClienteResposta;
	public Integer q;
	private JTextArea textArea;

	public ProvaListener(JTextArea paramTextArea, Vector<ClientInfo> getmClients) {
		textArea = paramTextArea;
		mapaClienteResposta = new HashMap<ClientInfo, Map<Integer,String>>();
		for (ClientInfo clientInfo : getmClients) {
			mapaClienteResposta.put(clientInfo, new HashMap<Integer, String>());			
		}
	}

	public void addResposta(ClientInfo mClientInfo, String message) {
		mapaClienteResposta.get(mClientInfo).put(q, message);
		
		textArea.append(mClientInfo + " respondeu " + message + "\n");
		textArea.append(quantosResponderam(q) + " já responderam\n");
		
	}

	private Integer quantosResponderam(int q) {
		int v = 0;
		for (ClientInfo c : getTodosClientes()) {
			if (mapaClienteResposta.get(c).get(q) != null)
				v++;
		}
		
		return v;
	}

	public Set<ClientInfo> getTodosClientes() {
		return mapaClienteResposta.keySet();
	}

	public String getResposta(ClientInfo clientInfo, int i) {
		return mapaClienteResposta.get(clientInfo).get(i);
	}

}
