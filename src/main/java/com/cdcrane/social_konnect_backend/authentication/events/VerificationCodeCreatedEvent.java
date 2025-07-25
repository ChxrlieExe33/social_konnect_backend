package com.cdcrane.social_konnect_backend.authentication.events;

import lombok.Getter;

@Getter
public class VerificationCodeCreatedEvent {

    private final String email;
    private final int verificationCode;

    public VerificationCodeCreatedEvent(String email, int verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }

}
