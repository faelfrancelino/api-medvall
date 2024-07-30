package med.vall.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.vall.api.dto.DadosCadastroMedico;
import med.vall.api.model.Medico;
import med.vall.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos") 
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico) {
		medicoRepository.save(new Medico(dadosCadastroMedico));
	}
	
}
