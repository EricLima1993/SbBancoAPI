package br.com.next;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.next.models.entities.Cliente;
import br.com.next.models.entities.Conta;
import br.com.next.models.repositories.ClienteRepository;
import br.com.next.models.repositories.ContaRepository;
//implements CommandLineRunner
@SpringBootApplication
public class NextWebApplication {

	@Autowired
	ClienteRepository cliR;
	@Autowired
	ContaRepository conR;
	
	public static void main(String[] args) {
		SpringApplication.run(NextWebApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		Conta con1 = new Conta(null,1000.00);
		Conta con2 = new Conta(null,1000.00);
		Conta con3 = new Conta(null,1000.00);
		
		Cliente cli1 = new Cliente(null,"Eric Lima","43746519835","123456","COMUM");
		Cliente cli2 = new Cliente(null,"Bruna Silveira","41078536010","654321","COMUM");
		Cliente cli3 = new Cliente(null,"Marcos Teixeira","52068723154","145236","COMUM");
		
		con1.setCliente(cli1);
		con2.setCliente(cli2);
		con3.setCliente(cli3);
		
		cli1.setConta(con1);
		cli2.setConta(con2);
		cli3.setConta(con3);
		
		cliR.saveAll(Arrays.asList(cli1,cli2,cli3));
		conR.saveAll(Arrays.asList(con1,con2,con3));
	}*/

}
