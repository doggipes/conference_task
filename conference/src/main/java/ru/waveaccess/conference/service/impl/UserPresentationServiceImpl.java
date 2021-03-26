package ru.waveaccess.conference.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.waveaccess.conference.exception.EntityUserPresentationExist;
import ru.waveaccess.conference.model.entity.UserPresentation;
import ru.waveaccess.conference.repository.UserPresentationRepository;
import ru.waveaccess.conference.service.interfaces.UserPresentationService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPresentationServiceImpl implements UserPresentationService {
    private final UserPresentationRepository userPresentationRepository;

    @Override
    public void save(Long user_id, Long presentation_id) {
        Optional<UserPresentation> optionalUserPresentation = userPresentationRepository.findByUserIdAndPresentationId(user_id, presentation_id);
        if(optionalUserPresentation.isPresent())
            throw new EntityUserPresentationExist("You have already joined");

        userPresentationRepository.save(UserPresentation.builder()
                                                        .userId(user_id)
                                                        .presentationId(presentation_id)
                                                        .build());
    }
}
