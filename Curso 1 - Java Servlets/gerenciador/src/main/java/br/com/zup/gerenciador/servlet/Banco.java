package br.com.zup.gerenciador.servlet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {

	private static List<Empresa> lista = new ArrayList<Empresa>();
	private static Integer chave = 1;
	static {
		Empresa empresa = new Empresa();
		empresa.setId(chave++);
		empresa.setNome("Facebook");

		Empresa empresa2 = new Empresa();
		empresa2.setId(chave++);
		empresa2.setNome("Google");
		
		Banco.lista.add(empresa);
		Banco.lista.add(empresa2);
	}
	
	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.chave++);
		lista.add(empresa);
		
	}
	
	public List<Empresa> getEmpresas(){
		return Banco.lista;
	}

	public void removeEmpresa(Integer id) {
		
		Iterator<Empresa> it = lista.iterator();
		while (it.hasNext()) {
			Empresa emp = it.next();
			if(emp.getId() == id) {
				it.remove();
			}
		}
		
	}

	public Empresa buscarPeloId(Integer id) {
		
		for(Empresa empresa : lista) {
			if(empresa.getId() == id) {
				return empresa;
			}
		}
		return null;
		
		
	}

	
}
