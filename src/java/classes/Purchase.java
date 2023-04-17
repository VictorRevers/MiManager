package classes;
import java.util.Date;

public class Purchase {
    public int id;
    public int id_cliente;
    public int id_product;
    public float value;
    public String data;
    public Cliente cliente;
    
    public Purchase(int id_cliente, int id_product, float value, String data){
        this.id_cliente = id_cliente;
        this.id_product = id_product;
        this.value = value;
        this.data = data;
    }
    
    public Purchase(String name, String tel, String data, float value){
        this.cliente =  new Cliente(name, tel);           
        this.data = data;
        this.value = value;     
    }   
}
