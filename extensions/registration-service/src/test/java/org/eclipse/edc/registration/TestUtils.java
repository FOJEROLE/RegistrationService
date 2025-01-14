/*
 *  Copyright (c) 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

package org.eclipse.edc.registration;

import org.eclipse.edc.registration.authority.model.Participant;
import org.eclipse.edc.registration.authority.model.ParticipantStatus;
import org.eclipse.edc.registration.model.ParticipantDto;
import org.eclipse.edc.registration.model.ParticipantStatusDto;

import java.util.Map;

import static java.lang.String.format;

public class TestUtils {


    public static ParticipantDto.Builder createParticipantDto() {
        return ParticipantDto.Builder.newInstance()
                .did(format("did:web:%s", "test-domain"))
                .status(ParticipantStatusDto.ONBOARDED);
    }

    public static ParticipantDto getParticipantDtoFromParticipant(Participant participant) {
        return ParticipantDto.Builder.newInstance()
                .did(participant.getDid())
                .status(modelToDtoStatusMap().get(participant.getStatus()))
                .build();
    }

    /**
     * Map of ParticipantStatus & ParticipantStatusDto.
     * It describes what should be DTO status in respect of domain model status.
     */
    private static Map<ParticipantStatus, ParticipantStatusDto> modelToDtoStatusMap() {
        return Map.of(
                ParticipantStatus.ONBOARDING_INITIATED, ParticipantStatusDto.ONBOARDING_IN_PROGRESS,
                ParticipantStatus.AUTHORIZING, ParticipantStatusDto.ONBOARDING_IN_PROGRESS,
                ParticipantStatus.AUTHORIZED, ParticipantStatusDto.ONBOARDING_IN_PROGRESS,
                ParticipantStatus.ONBOARDED, ParticipantStatusDto.ONBOARDED,
                ParticipantStatus.FAILED, ParticipantStatusDto.DENIED,
                ParticipantStatus.DENIED, ParticipantStatusDto.DENIED
        );
    }
}
