package uniandes.edu.co.demo.modelo;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="puntos_atencion")
@ToString
public class PuntoAtencion {

    @Id
    private int id;

    private String tipoPunto;

    public PuntoAtencion(){}
    
    public PuntoAtencion(int id, String tipoPunto) {
        this.id = id;
        this.tipoPunto = tipoPunto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_punto() {
        return tipoPunto;
    }

    public void setTipo_punto(String tipoPunto) {
        this.tipoPunto = tipoPunto;
    }

}
