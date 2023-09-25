package org.alberto.cinema.mapper;

import org.alberto.cinema.dto.response.SalaDTOResponse;
import org.alberto.cinema.model.Sala;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SalaMapper {
	
	public SalaDTOResponse toSalaDTOResponse (Sala s) {
		SalaDTOResponse sDTOResp = new SalaDTOResponse();
		sDTOResp.setCapienza(s.getCapienza());
		sDTOResp.setNomeSala(s.getNomeSala());
		return sDTOResp;
	}
	
	public List<SalaDTOResponse> toSalaDTOResponseList(List<Sala> sale){
		return sale.stream().map(this::toSalaDTOResponse).toList();
	}

}
