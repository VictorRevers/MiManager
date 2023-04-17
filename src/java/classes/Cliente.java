package classes;

public class Cliente {
   public int id;
   public String name;
   public String tel;
   public String address;
   
   public Cliente(int id, String name, String tel, String address){
       this.id = id;
       this.name = name;
       this.tel = tel;
       this.address = address;
   }
   
   public Cliente(String name, String tel, String address){
       this.name = name;
       this.tel = tel;
       this.address = address;
   }
   
   public Cliente(String name, String tel){
       this.name = name;
       this.tel = tel;
   }
}
