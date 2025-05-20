package it.daylight.MyCashWebApp.service;

import it.daylight.MyCashWebApp.dto.IncomeRequestDTO;
import it.daylight.MyCashWebApp.dto.IncomeResponseDTO;
import it.daylight.MyCashWebApp.entity.Income;
import it.daylight.MyCashWebApp.entity.IncomeCategories;
import it.daylight.MyCashWebApp.entity.User;
import it.daylight.MyCashWebApp.repository.IncomeRepository;
import it.daylight.MyCashWebApp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeService {

	private final IncomeRepository incomeRepository;
	private final UserRepository userRepository;

	//registra una nuova entrata
	@Transactional
	public IncomeResponseDTO createIncome(IncomeRequestDTO incomeRequestDTO) {

		//verifica che l'utente esista
		User user = userRepository.findById(incomeRequestDTO.getUserId())
			.orElseThrow(() -> new EntityNotFoundException(
			"User not found with ID: " + incomeRequestDTO.getUserId()));

		//crea una nuova entrata
		Income income = new Income();
		BeanUtils.copyProperties(incomeRequestDTO, income);
		income.setUser(user);

		//salva la nuova entrata
		incomeRepository.save(income);
		return convertToResponseDTO(income);
	}

	//recupera tutte le entrate
	public List<IncomeResponseDTO> getAllIncomes() {
		return incomeRepository.findAll().stream().map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	//recupera una singola entrata
	public IncomeResponseDTO getIncomeById(Long id) {
		Income income = incomeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Income not found with ID: " + id));
		return convertToResponseDTO(income);
	}

	//recupera le entrate di un singolo utente
	public List<IncomeResponseDTO> getIncomesByUserId(Long userId) {
		return incomeRepository.findByUserId(userId).stream()
			.map(this::convertToResponseDTO)
			.collect(Collectors.toList());
	}

	//recupera le entrate filtrate
	public List<IncomeResponseDTO> getFilteredIncomes(IncomeCategories incomeCategories , Date date, Double amount, User user) {
		return incomeRepository.findByIncomeCategoriesAndDateAndAmountAndUser(incomeCategories, date, amount, user).stream()
				.map(this::convertToResponseDTO)
				.collect(Collectors.toList());
	}

	//recupera le entrate per data
	public List<IncomeResponseDTO> getIncomesByDate(Date date) {
		return incomeRepository.findByDate(date).stream()
			.map(this::convertToResponseDTO)
			.collect(Collectors.toList());
	}

	//recupera le entrate per categoria
	public List<IncomeResponseDTO> getIncomesByCategory(IncomeCategories incomeCategories) {
		return incomeRepository.findByIncomeCategories(incomeCategories).stream()
			.map(this::convertToResponseDTO)
			.collect(Collectors.toList());
	}

	//recupera le entrate per importo
	public List<IncomeResponseDTO> getIncomesByAmount(Double amount) {
		return incomeRepository.findByAmount(amount).stream()
			.map(this::convertToResponseDTO)
			.collect(Collectors.toList());
	}

	//modifica una singola entrata
	@Transactional
	public IncomeResponseDTO updateIncome(Long id, IncomeRequestDTO incomeRequestDTO) {
		Income income = incomeRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Income not found with ID: " + id));
		BeanUtils.copyProperties(incomeRequestDTO, income);
		incomeRepository.save(income);
		return convertToResponseDTO(income);
	}

	//elimina una singola entrata
	@Transactional
	public void deleteIncome(Long id) {
		incomeRepository.deleteById(id);
	}


	//metodo utilizzato per convertire un oggetto Income in un oggetto IncomeResponseDTO
	private IncomeResponseDTO convertToResponseDTO(Income income) {
		IncomeResponseDTO dto = new IncomeResponseDTO();
		BeanUtils.copyProperties(income, dto);
		dto.setUserName(income.getUser().getName() + " " + income.getUser().getSurname());
		return dto;
	}

}
