package com.daily_notes.notes.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Getter
@Setter
@DynamoDbBean
public class UserEntity {

    @Id
    private String id;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String note;
    private String status;
    private Integer tokenVersion;
}
