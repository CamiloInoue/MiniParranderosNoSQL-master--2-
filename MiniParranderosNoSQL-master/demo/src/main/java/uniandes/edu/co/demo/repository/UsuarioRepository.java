package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Integer>{
    
    @Query("{num_doc: ?0}")
    List<Usuario> buscarPorNumDoc(int num_doc);

}
