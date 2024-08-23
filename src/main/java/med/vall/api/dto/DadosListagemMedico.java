package med.vall.api.dto;

import med.vall.api.domain.Medico;
import med.vall.api.enuns.EspecialidadeEnum;

public record DadosListagemMedico(Long id, String nome, String email, String crm, EspecialidadeEnum especialidade) {

	public DadosListagemMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}

}
