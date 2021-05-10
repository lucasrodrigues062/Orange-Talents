package br.com.alura.jpa.testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TesteSQL {
	public static void main(String[] args) throws Exception {		
		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost/alura_jpa?serverTimezone=UTC", "root", "");
		String sql = "insert into Conta(agencia, numero, titular, saldo) values(?,?,?,?)";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, 1234);
		ps.setInt(2, 4321);
		ps.setString(3, "Jonas");
		ps.setDouble(4, 900.0);
		
		ps.execute();		
		System.out.println("Inserido com sucesso!");
	}
}