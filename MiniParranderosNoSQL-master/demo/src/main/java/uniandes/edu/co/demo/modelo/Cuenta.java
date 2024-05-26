package uniandes.edu.co.demo.modelo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="cuentas")
@ToString
public class Cuenta {
    
    @Id
    private int numero_cuenta;

    private int num_doc_cliente;

    private Double saldo;

    private Date fecha_Creacion;

    private Date ultima_transaccion;

    private String estado;

    private String tipo;

    private List<OperacionCuenta> operaciones_cuenta;


    public Cuenta(Double saldo,Date fechaCreacion, Date ultima_transaccion, String estado, String tipo, List<OperacionCuenta> operaciones_cuenta, int num_doc_cliente) {
        this.saldo = saldo;
        this.fecha_Creacion = fechaCreacion;
        this.ultima_transaccion = ultima_transaccion;
        this.estado = estado;
        this.operaciones_cuenta = operaciones_cuenta;
        this.tipo = tipo;
        this.num_doc_cliente = num_doc_cliente;
    }

    public Cuenta() {
    }

    public int getNum_doc_cliente() {
        return num_doc_cliente;
    }

    public void setNum_doc_cliente(int num_doc_cliente) {
        this.num_doc_cliente = num_doc_cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public int getNumero_cuenta() {
        return numero_cuenta;
    }
    public void setNumero_cuenta(int numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Double setSaldo(Double saldo) {
        return this.saldo = saldo;
    }

    public Date getUltima_transaccion() {
        return ultima_transaccion;
    }

    public void setUltima_transaccion(Date ultima_transaccion) {
        this.ultima_transaccion = ultima_transaccion;
    }

    public Date getFechaCreacion() {
        return fecha_Creacion;
    }

    public void setFechaCreacion(Date fecha) {
        this.fecha_Creacion = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<OperacionCuenta> getOperaciones_cuenta() {
        return operaciones_cuenta;
    }

    public void setOperaciones_cuenta(List<OperacionCuenta> operaciones_cuenta) {
        this.operaciones_cuenta = operaciones_cuenta;
    }

}
