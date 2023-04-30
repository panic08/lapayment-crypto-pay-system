package ru.panic.lapayment.template.service;

import ru.panic.lapayment.template.dto.factory.ConclusionRequestDto;
import ru.panic.lapayment.template.entity.Conclusion;

public interface ConclusionService {
    Conclusion createConclusion(ConclusionRequestDto request);
    Conclusion deleteConclusion(Conclusion conclusion);
}
