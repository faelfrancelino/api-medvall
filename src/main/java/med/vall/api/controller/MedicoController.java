package med.vall.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.vall.api.dto.DadosCadastroMedico;
import med.vall.api.dto.DadosListagemMedico;
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
	
	@GetMapping
	public Page<DadosListagemMedico> listar(Pageable paginacao){
		//na url, utilizar ?size=10&page=2 (paginacao de 10 em 10, buscando página 2).
		//ordernar: ?sort=crm,desc por exemplo
		//Podemos alterar os defaults:
		//	public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort={"nome,desc"}) Pageable paginacao){

        return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);

	}
	
}
