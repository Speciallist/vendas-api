package io.github.Speciallist.vendasapi.rest.dashboard;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.Speciallist.vendasapi.model.repository.ClienteRepository;
import io.github.Speciallist.vendasapi.model.repository.ProdutoRepository;
import io.github.Speciallist.vendasapi.model.repository.VendaRepository;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("http://localhost:3000")
public class DashboardController {

	@Autowired
	private VendaRepository vendas;

	@Autowired
	private ClienteRepository clientes;

	@Autowired
	private ProdutoRepository produtos;

	@GetMapping
	public DashboardData getDashboard() {
		long vendasCount = vendas.count();
		long clientesCount = clientes.count();
		long produtosCount = produtos.count();
		int anoAtual = LocalDate.now().getYear();
		var vendasPorMes = vendas.obterSomatoriaVendasPorMes(anoAtual);
		return new DashboardData(produtosCount, clientesCount, vendasCount, vendasPorMes);
	}

}
