package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Cuenta;

public interface CuentaRepository extends MongoRepository<Cuenta,Integer>{
    
    @Query("{_id: ?0}")
    Cuenta darCuentaPorId(int id_cuenta);

}
