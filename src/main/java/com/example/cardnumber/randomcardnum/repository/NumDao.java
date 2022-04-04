package com.example.cardnumber.randomcardnum.repository;


import java.io.Serializable;

//@Repository
public class NumDao implements Serializable {

//    public static final String HASH_KEY = "Num";
//
//    @Autowired
//    private RedisTemplate template;
//
//    public Num save(Num num) {
//        template.opsForHash().put(HASH_KEY,num.getNuumero(),num);
//        return num;
//    }
//
//    public List<Num> findAll(){
//        return template.opsForHash().values(HASH_KEY);
//    }
//    public  Num findNumById(int id){
//        return(Num) template.opsForHash().get(HASH_KEY,id);
//    }
//    public String deleteNumeroById(int id){
//        template.opsForHash().delete(HASH_KEY,id);
//        return "num deletado!"+id;
//    }

    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
