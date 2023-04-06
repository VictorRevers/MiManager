
package classes;


public class Product {
    public int id;
    public String name;
    public float value;
    
    public Product(int id, String name, float value){
        this.id = id;
        this.name = name;
        this.value = value;
    }
    
    public Product(String name, float value){
        this.name = name;
        this.value = value;
    }
}
