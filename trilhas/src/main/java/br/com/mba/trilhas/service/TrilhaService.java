package br.com.mba.trilhas.service;

import br.com.mba.trilhas.dto.TrilhaDto;
import br.com.mba.trilhas.model.Trilha;
import br.com.mba.trilhas.repository.TrilhaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TrilhaService {

    @Autowired
    private TrilhaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retornar todos as trilhas cadastradas no Banco de dados com paginação
     * @param paginacao
     * @return
     */
    public Page<TrilhaDto> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, TrilhaDto.class));
    }

    /**
     * Retornar as trilhas cadastradas no Banco de dados por id
     * @param id
     * @return
     */
    public TrilhaDto obterPorId(Long id) {
        Trilha trilha = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(trilha, TrilhaDto.class);
    }

    /**
     * Criar as trilhas no Banco de dados
     * @param dto
     * @return
     */
    public TrilhaDto criarTrilha(TrilhaDto dto) {
        Trilha trilha = modelMapper.map(dto, Trilha.class);
        repository.save(trilha);

        return modelMapper.map(trilha, TrilhaDto.class);
    }

    /**
     * Atualizar a trilha no Banco de dados
     * @param id
     * @param dto
     * @return
     */
    public TrilhaDto atualizarTrilha(Long id, TrilhaDto dto) {
        Trilha trilha = modelMapper.map(dto, Trilha.class);
        trilha.setId(id);
        trilha = repository.save(trilha);
        return modelMapper.map(trilha, TrilhaDto.class);
    }

    /**
     * Excluir a trilha no Banco de dados
     * @param id
     */
    public void excluirTrilha(Long id) {
        repository.deleteById(id);
    }

}
