package io.github.Speciallist.vendasapi.rest.vendas;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.Speciallist.vendasapi.model.Venda;
import io.github.Speciallist.vendasapi.model.repository.ItemVendaRepository;
import io.github.Speciallist.vendasapi.model.repository.VendaRepository;
import io.github.Speciallist.vendasapi.service.RelatorioVendasService;
import io.github.Speciallist.vendasapi.util.DateUtils;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin("http://localhost:3000")
public class VendasController {

	@Autowired
	private VendaRepository repository;

	@Autowired
	private ItemVendaRepository itemVendaRepository;

	@Autowired
	private RelatorioVendasService relatorioVendasService;

	@PostMapping
	@Transactional
	public void realizarVenda(@RequestBody Venda venda) {
		repository.save(venda);
		venda.getItens().stream().forEach(itemVenda -> itemVenda.setVenda(venda));
		itemVendaRepository.saveAll(venda.getItens());
	}

	@GetMapping("/relatorio-vendas")
	public ResponseEntity<byte[]> relatorioVendas(
			@RequestParam(value = "id", required = false, defaultValue = "") Long idCliente,
			@RequestParam(value = "inicio", required = false, defaultValue = "") String dataInicio,
			@RequestParam(value = "fim", required = false, defaultValue = "") String dataFim, Object fim,
			Object inicio) {
		Date dataInicioDate = DateUtils.fromString(dataInicio);
		Date dataFimDate = DateUtils.fromString(dataFim, true);
		if (dataInicioDate == null) {
			dataInicioDate = DateUtils.DATA_INICIO_PADRAO;
		}
		if (dataFimDate == null) {
			dataFimDate = DateUtils.hoje(true);
		}
		byte[] relatorioGerado = relatorioVendasService.gerarRelatorio(idCliente, dataInicioDate, dataFimDate);
		String fileName = "relatorio-vendas.pdf";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("inline; filename=\"" + fileName + "\"", fileName);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentType(MediaType.APPLICATION_PDF);
		var responseEntity = new ResponseEntity<>(relatorioGerado, headers, HttpStatus.OK);
		return responseEntity;
	}

}
