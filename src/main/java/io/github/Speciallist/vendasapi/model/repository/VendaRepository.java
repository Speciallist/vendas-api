package io.github.Speciallist.vendasapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.Speciallist.vendasapi.model.Venda;
import io.github.Speciallist.vendasapi.model.repository.projections.VendaPorMes;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	@Query(value = "SELECT EXTRACT(MONTH FROM v.data_venda) AS mes, " + "SUM(v.total) AS valor " + "FROM venda as v "
			+ "WHERE EXTRACT (YEAR FROM v.data_venda) = :ano " + "GROUP BY EXTRACT(MONTH FROM v.data_venda) "
			+ "ORDER BY EXTRACT(MONTH FROM v.data_venda)", nativeQuery = true)
	List<VendaPorMes> obterSomatoriaVendasPorMes(@Param("ano") Integer ano);

}
