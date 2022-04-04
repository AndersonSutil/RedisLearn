package com.example.cardnumber.randomcardnum.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Num")
public class Num implements Serializable {
    @Id
    private int nuumero;
//    private UUID uuid;

}
