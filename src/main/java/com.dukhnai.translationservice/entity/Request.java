package com.dukhnai.translationservice.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Request {

    String id;

    Date dateWithTime;

    String text;

    String fromLanguage;

    String toLanguage;

    String clientIp;
}
