package com.chrispbacon.chrispbaconend.model;

import java.util.List;
import java.util.UUID;

public record CorrectionDto(UUID questionId,
                            List<UUID> userAnswer,
                            List<UUID> correctAnswer,
                            boolean isCorrect) {
}
