package med.vall.api.dto;

import med.vall.api.enuns.EspecialidadeEnum;
import med.vall.api.model.Medico;

public record DadosListagemMedico(String nome, String email, String crm, EspecialidadeEnum especialidade) {

	public DadosListagemMedico(Medico medico) {
		this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}

}
