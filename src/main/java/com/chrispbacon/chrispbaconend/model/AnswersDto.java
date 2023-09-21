package com.chrispbacon.chrispbaconend.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

public record AnswersDto (UUID questionId, List<UUID> answers){
}
