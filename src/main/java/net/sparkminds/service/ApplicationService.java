package net.sparkminds.service;

import java.util.List;

import net.sparkminds.service.dto.request.ApplicationRequestDTO;
import net.sparkminds.service.dto.response.ApplicationResponseDTO;

public interface ApplicationService {
	List<ApplicationResponseDTO> getAllApplication(String jwt);
	List<ApplicationResponseDTO> getApplicationById(Long id, String jwt);
	ApplicationResponseDTO createApplication(ApplicationRequestDTO applicationRequestDTO);
	ApplicationResponseDTO updateApplication(Long id, ApplicationRequestDTO applicationRequestDTO);
}
