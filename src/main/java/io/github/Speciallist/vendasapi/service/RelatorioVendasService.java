package io.github.Speciallist.vendasapi.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class RelatorioVendasService {

	@Value("classpath:reports/relatorio-vendas.jrxml")
	private Resource relatorioVendasSource;

	@Value("classpath:reports/relatorio-vendas.jasper")
	private Resource relatorioVendasCompilado;

	@Autowired
	private DataSource dataSource;

	// apresenta o mesmo resultado do método gerarRelatorioCompilado()
	// porém utiliza o JasperRunManager em vez do JasperFillManager
	public byte[] gerarRelatorio(Long idCliente, Date dataInicio, Date dataFim) {
		// try with resources
		try (Connection connection = dataSource.getConnection();) {
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("CLIENTE_ID", idCliente);
			parameters.put("DATA_INICIO", dataInicio);
			parameters.put("DATA_FIM", dataFim);
			return JasperRunManager.runReportToPdf(relatorioVendasCompilado.getInputStream(), parameters, connection);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// método utiliza o arquivo compilado no JasperStudio
	// portanto o backend não precisa compilar o arquivo jrxml
	public byte[] gerarRelatorioCompilado() {
		// try with resources
		try (Connection connection = dataSource.getConnection();) {
			Map<String, Object> parameters = new HashMap<>();
			JasperPrint print = JasperFillManager.fillReport(relatorioVendasCompilado.getInputStream(), parameters,
					connection);
			return JasperExportManager.exportReportToPdf(print);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// método utiliza o arquivo jrxml criado no JasperStudio e faz a compilação no
	// backend
	// pode atrasar a entrega do relatório gerado, devido a compilação ser executada
	// em cada requisição
	public byte[] gerarRelatorioCompilando() {
		// try with resources
		try (Connection connection = dataSource.getConnection();) {
			JasperReport compiledReport = JasperCompileManager.compileReport(relatorioVendasSource.getInputStream());
			Map<String, Object> parameters = new HashMap<>();
			JasperPrint print = JasperFillManager.fillReport(compiledReport, parameters, connection);
			return JasperExportManager.exportReportToPdf(print);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
