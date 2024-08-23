package med.vall.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.vall.api.domain.Medico;
import med.vall.api.dto.DadosAtualizacaoMedico;
import med.vall.api.dto.DadosCadastroMedico;
import med.vall.api.dto.DadosDetalhamentoMedico;
import med.vall.api.dto.DadosListagemMedico;
import med.vall.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos") 
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		medicoRepository.save(medico);
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao){
		//na url, utilizar ?size=10&page=2 (paginacao de 10 em 10, buscando página 2).
		//ordernar: ?sort=crm,desc por exemplo
		//Podemos alterar os defaults:
		//	public Page<DadosListagemMedico> listar(@PageableDefault(size=10, sort={"nome,desc"}) Pageable paginacao){
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
	}
	
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
    	var medico = medicoRepository.getReferenceById(dados.id());
    	medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    //exclusao física
//    @DeleteMapping("/{id}") 
//    @Transactional
//    public void excluir(@PathVariable Long id) {
//    	medicoRepository.deleteById(id);
//    }
    
    @DeleteMapping("/{id}")//exclusao lógica
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id) {
    	var medico = medicoRepository.getReferenceById(id);
    	medico.excluir();
    	
    	return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
    	var medico = medicoRepository.getReferenceById(id);
    	return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
    
    
}
