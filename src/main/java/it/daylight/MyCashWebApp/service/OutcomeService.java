package it.daylight.MyCashWebApp.service;

import it.daylight.MyCashWebApp.dto.OutcomeRequestDTO;
import it.daylight.MyCashWebApp.dto.OutcomeResponseDTO;
import it.daylight.MyCashWebApp.entity.Outcome;
import it.daylight.MyCashWebApp.entity.OutcomeCategories;
import it.daylight.MyCashWebApp.entity.User;
import it.daylight.MyCashWebApp.repository.OutcomeRepository;
import it.daylight.MyCashWebApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutcomeService {

	private final OutcomeRepository outcomeRepository;
	private final UserRepository userRepository;

	//crea una nuova uscita
	@Transactional
	public OutcomeResponseDTO createOutcome(OutcomeRequestDTO outcomeRequestDTO) {

		//verifica che l'utente esista
		User user = userRepository.findById(outcomeRequestDTO.getUserId())
			.orElseThrow(() -> new IllegalArgumentException(
				"User not found with ID: " + outcomeRequestDTO.getUserId()));

		//crea una nuova uscita
		it.daylight.MyCashWebApp.entity.Outcome outcome = new it.daylight.MyCashWebApp.entity.Outcome();
		outcome.setDescription(outcomeRequestDTO.getDescription());
		outcome.setAmount(outcomeRequestDTO.getAmount());
		outcome.setDate(outcomeRequestDTO.getDate());
		outcome.setOutcomeCategories(outcomeRequestDTO.getOutcomeCategories());
		outcome.setExpirationDate(outcomeRequestDTO.getExpirationDate());
		outcome.setUser(user);
		outcomeRepository.save(outcome);

		return convertToResponseDTO(outcome);

	}

	//recupera tutte le uscite
	public List<OutcomeResponseDTO> getAllOutcomes() {
		return outcomeRepository.findAll().stream()
			.map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	//recupera una singola uscita
	public OutcomeResponseDTO getOutcomeById(Long id) {
		return outcomeRepository.findById(id).map(this::convertToResponseDTO).orElse(null);
	}

	//recupera le uscite di un singolo utente
	public List<OutcomeResponseDTO> getOutcomesByUserId(Long userId) {
		return outcomeRepository.findByUserId(userId).stream()
			.map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	//recupera le uscite per data
	public List<OutcomeResponseDTO> getOutcomesByDate(Date date) {
		return outcomeRepository.findByDate(date).stream()
			.map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	//recupera le uscite per importo
	public List<OutcomeResponseDTO> getOutcomesByAmount(Double amount) {
		return outcomeRepository.findByAmount(amount).stream()
			.map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	//recupera le uscite per categoria
	public List<OutcomeResponseDTO> getOutcomesByCategory(OutcomeCategories outcomeCategories) {
		return outcomeRepository.findByOutcomeCategories(outcomeCategories).stream()
			.map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	//recupera le uscite per scadenza
	public List<OutcomeResponseDTO> getOutcomesByExpirationDate(Date expirationDate) {
		return outcomeRepository.findByExpirationDate(expirationDate).stream()
				.map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	//recupera le uscite filtrate
	public List<OutcomeResponseDTO> getFilteredOutcomes(OutcomeCategories outcomeCategories, Date date, Double amount, Date expirationDate, User user) {
		return outcomeRepository.findByOutcomeCategoriesAndDateAndAmountAndExpirationDateAndUser(outcomeCategories, date, amount, expirationDate, user).stream()
				.map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	//modifica una uscita
	@Transactional
	public OutcomeResponseDTO updateOutcome(Long id, OutcomeRequestDTO outcomeRequestDTO) {
		Outcome outcome = outcomeRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Outcome not found with ID: " + id));
		BeanUtils.copyProperties(outcomeRequestDTO, outcome);
		outcomeRepository.save(outcome);
		return convertToResponseDTO(outcome);
	}

	//elimina una uscita
	@Transactional
	public void deleteOutcome(Long id) {
		outcomeRepository.deleteById(id);
	}

	private OutcomeResponseDTO convertToResponseDTO (Outcome outcome){
			OutcomeResponseDTO dto = new OutcomeResponseDTO();
			BeanUtils.copyProperties(outcome, dto);
			dto.setUserName(outcome.getUser().getName() + " " + outcome.getUser().getSurname());
			return dto;
	}
}
