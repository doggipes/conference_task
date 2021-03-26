package ru.waveaccess.conference.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.waveaccess.conference.exception.PresentationEntityNotFound;
import ru.waveaccess.conference.model.entity.Presentation;
import ru.waveaccess.conference.model.form.PresentationForm;
import ru.waveaccess.conference.repository.PresentationRepository;
import ru.waveaccess.conference.repository.UserPresentationRepository;
import ru.waveaccess.conference.service.interfaces.PresentationService;
import ru.waveaccess.conference.service.interfaces.UserPresentationService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PresentationServiceImpl implements PresentationService {
    private final PresentationRepository presentationRepository;
    private final UserPresentationService userPresentationService;


    @Override
    public void createPresentation(PresentationForm presentationForm, Long userId) {
        Presentation presentation = presentationRepository.save(Presentation.builder()
                                                            .name(presentationForm.getName())
                                                            .build());
        userPresentationService.save(userId, presentation.getId());
    }

    @Override
    public Presentation findById(Long id) {
        Optional<Presentation> presentation = presentationRepository.findById(id);
        if (!presentation.isPresent())
            throw new PresentationEntityNotFound("Presentation doesn't exist");
        return presentation.get();
    }

    @Override
    public Presentation findByName(String name) {
        Optional<Presentation> presentation = presentationRepository.findByName(name);
        if (!presentation.isPresent())
            throw new PresentationEntityNotFound("Presentation doesn't exist");
        return presentation.get();
    }

    @Override
    public Map<String, String> getMapWithAllPresentations() {
        return presentationRepository.findAll().stream().collect(Collectors.toMap(s -> String.valueOf(s.getId()), Presentation::getName));
    }

    @Override
    public List<Presentation> getAllPresentations() {
        return presentationRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Optional<Presentation> optionalPresentation = presentationRepository.findById(id);
        if (!optionalPresentation.isPresent())
            throw new EntityNotFoundException("Presentation doesn't exist");

        presentationRepository.delete(optionalPresentation.get());
    }

    @Override
    @Transactional
    public void updateName(Long id, String name) {
        Optional<Presentation> optionalPresentation = presentationRepository.findById(id);
        if (!optionalPresentation.isPresent())
            throw new EntityNotFoundException("Presentation doesn't exist");

        presentationRepository.updatePresentationName(name, id);
    }
}
