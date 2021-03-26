package ru.waveaccess.conference.service.interfaces;

import ru.waveaccess.conference.model.entity.Presentation;
import ru.waveaccess.conference.model.form.PresentationForm;

import java.util.List;
import java.util.Map;

public interface PresentationService {
    void createPresentation(PresentationForm presentationForm, Long userId);

    Presentation findById(Long id);

    Presentation findByName(String name);

    Map<String, String> getMapWithAllPresentations();

    List<Presentation> getAllPresentations();

    void delete(Long id);

    void updateName(Long id, String name);
}
