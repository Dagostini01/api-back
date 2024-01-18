package br.com.projeto.api.repository;

import br.com.projeto.api.model.Empresa;
import org.springframework.data.repository.CrudRepository;
@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Empresa, Long> {

}
