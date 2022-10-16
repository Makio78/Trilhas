package br.com.mba.trilhas.controller;

import br.com.mba.trilhas.dto.TrilhaDto;
import br.com.mba.trilhas.service.TrilhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/trilhas")
public class TrilhaController {

    @Autowired
    private TrilhaService service;

    @GetMapping
    public Page<TrilhaDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaDto> detalhar(@PathVariable @NotNull Long id) {
        TrilhaDto dto = service.obterPorId(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TrilhaDto> cadastrar(@RequestBody @Valid TrilhaDto dto, UriComponentsBuilder uriBuilder) {
        TrilhaDto trilha = service.criarTrilha(dto);
        URI endereco = uriBuilder.path("/trilhas/{id}").buildAndExpand(trilha.getId()).toUri();

        return ResponseEntity.created(endereco).body(trilha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid TrilhaDto dto) {
        TrilhaDto atualizado = service.atualizarTrilha(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrilhaDto> remover(@PathVariable @NotNull Long id) {
        service.excluirTrilha(id);
        return ResponseEntity.noContent().build();
    }

}
