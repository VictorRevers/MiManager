package classes;
import java.util.Date;

public class Purchase {
    public int id;
    public int id_cliente;
    public int id_product;
    public float value;
    public Date data;
    
    public Purchase(int id_cliente, int id_product, float value, Date data){
        this.id_cliente = id_cliente;
        this.id_product = id_product;
        this.value = value;
        this.data = data;
    }
}
