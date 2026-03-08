package com.example.reciclo.controller.rest;

import com.example.reciclo.dto.PontoDeColetaDTO;
import com.example.reciclo.dto.PontoDeColetaResponseDTO;
import com.example.reciclo.mapper.PontoDeColetaMapper;
import com.example.reciclo.model.PontoDeColeta;
import com.example.reciclo.repository.PontoDeColetaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pontos-coleta")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PontoDeColetaRestController {

    private final PontoDeColetaRepository pontoRepository;
    private final PontoDeColetaMapper pontoMapper;

    public PontoDeColetaRestController(PontoDeColetaRepository pontoRepository, PontoDeColetaMapper pontoMapper) {
        this.pontoRepository = pontoRepository;
        this.pontoMapper = pontoMapper;
    }

    /**
     * GET /api/v1/pontos-coleta - Listar todos os pontos de coleta
     */
    @GetMapping
    public ResponseEntity<List<PontoDeColetaResponseDTO>> listarTodos() {
        List<PontoDeColeta> pontos = pontoRepository.findAll();
        List<PontoDeColetaResponseDTO> dtos = pontos.stream()
                .map(pontoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * GET /api/v1/pontos-coleta/{id} - Obter ponto de coleta por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PontoDeColetaResponseDTO> obterPorId(@PathVariable Long id) {
        return pontoRepository.findById(id)
                .map(ponto -> ResponseEntity.ok(pontoMapper.toResponseDTO(ponto)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/pontos-coleta/tipo/{tipo} - Listar pontos de coleta por tipo
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PontoDeColetaResponseDTO>> listarPorTipo(@PathVariable String tipo) {
        List<PontoDeColeta> pontos = pontoRepository.findByTipoPonto(tipo);
        List<PontoDeColetaResponseDTO> dtos = pontos.stream()
                .map(pontoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * POST /api/v1/pontos-coleta - Criar novo ponto de coleta
     */
    @PostMapping
    public ResponseEntity<PontoDeColetaResponseDTO> criar(@Valid @RequestBody PontoDeColetaDTO dto) {
        PontoDeColeta ponto = pontoMapper.toEntity(dto);
        PontoDeColeta pontoSalvo = pontoRepository.save(ponto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pontoMapper.toResponseDTO(pontoSalvo));
    }

    /**
     * PUT /api/v1/pontos-coleta/{id} - Atualizar ponto de coleta
     */
    @PutMapping("/{id}")
    public ResponseEntity<PontoDeColetaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PontoDeColetaDTO dto) {

        return pontoRepository.findById(id)
                .map(ponto -> {
                    ponto.setNomePonto(dto.getNomePonto());
                    ponto.setEndereco(dto.getEndereco());
                    ponto.setTipoPonto(dto.getTipoPonto());
                    PontoDeColeta pontoAtualizado = pontoRepository.save(ponto);
                    return ResponseEntity.ok(pontoMapper.toResponseDTO(pontoAtualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/v1/pontos-coleta/{id} - Deletar ponto de coleta
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar ponto de coleta", description = "Remove um ponto de coleta da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ponto de coleta deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ponto de coleta não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pontoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pontoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
