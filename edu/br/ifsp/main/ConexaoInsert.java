package edu.br.ifsp.main;


import br.edu.ifsp.controlador.InserePessoaController;
import br.edu.ifsp.tela.FramePrincipal;

public class ConexaoInsert {

	public static void main(String[] args)  {
		
			FramePrincipal fp = new FramePrincipal();
			InserePessoaController ipc = new InserePessoaController(fp);
			fp.setVisible(true);
		
	}

}
