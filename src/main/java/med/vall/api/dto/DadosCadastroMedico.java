package med.vall.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vall.api.enuns.EspecialidadeEnum;

public record DadosCadastroMedico(

		@NotBlank
		String nome,
		
		@NotBlank
		@Email
		String email, 
		
		@NotBlank
		String telefone,
		
		@NotBlank
		@Pattern(regexp = "\\d{4,6}") //4 a 6 dígitos
		String crm, 
		
		@NotNull
		EspecialidadeEnum especialidade, 
		
		@NotNull
		@Valid //valida o filho também
		DadosEndereco endereco) {

}