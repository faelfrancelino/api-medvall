package med.vall.api.dto;

import med.vall.api.domain.Endereco;
import med.vall.api.domain.Medico;
import med.vall.api.enuns.EspecialidadeEnum;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, EspecialidadeEnum especialidade, Endereco endereco) {
    public DadosDetalhamentoMedico (Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());

    }
}