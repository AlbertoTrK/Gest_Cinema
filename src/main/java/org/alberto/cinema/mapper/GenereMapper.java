package org.alberto.cinema.mapper;

import java.util.List;

import org.alberto.cinema.businesslogic.utilities.Utilities;
import org.alberto.cinema.dto.request.GenereDTO;
import org.alberto.cinema.model.Genere;
import org.springframework.stereotype.Component;

@Component
public class GenereMapper {
	
	public GenereDTO toGenereDTO(Genere g) {
		GenereDTO gDto = new GenereDTO();
		String nome = Utilities.rendiInizMaiuscERestoMin(g.getName());
		gDto.setName(nome);
		gDto.setDeleted(g.getDeleted());
		return gDto;
	}
	
	public List<GenereDTO> toGenereDTOList(List<Genere> listaGeneri){
		return listaGeneri.stream().map(this::toGenereDTO).toList();
	}

}
