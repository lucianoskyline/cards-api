package com.cards.service;

import com.cards.exception.BusinessException;
import com.cards.model.Accounts;
import com.cards.repository.AccountsRepository;
import com.cards.request.AccountsCancelRequest;
import com.cards.request.AccountsCreateRequest;
import com.cards.response.AccountsCancelResponse;
import com.cards.response.AccountsCreateResponse;
import com.cards.util.AccountsStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;


    public AccountsCreateResponse create(AccountsCreateRequest request) {
        if (accountsRepository.existsByCpf(request.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        if (accountsRepository.existsByCellphone(request.getCellphone())) {
            throw new BusinessException("Celular já cadastrado");
        }
        if (accountsRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }

        var modelMapper = new ModelMapper();
        var account = modelMapper.map(request, Accounts.class);
        account.setRegistrationDate(LocalDateTime.now());
        account.setInternalCode(UUID.randomUUID().toString());
        account.setStatus(AccountsStatus.ACTIVE.getValue());
        account = accountsRepository.save(account);

        return modelMapper.map(account, AccountsCreateResponse.class);
    }

    public AccountsCancelResponse cancel(AccountsCancelRequest request) {
        var account = accountsRepository.findByInternalCode(request.getInternalCode());
        if (account == null) {
            throw new RuntimeException("Conta não encontrada");
        }
        if (account.getStatus() == AccountsStatus.CANCELLED.getValue()) {
            throw new BusinessException("A conta já esta cancelada");
        }

        account.setCancelDate(LocalDateTime.now());
        account.setCancelDescription(request.getDescription());
        account.setStatus(AccountsStatus.CANCELLED.getValue());
        account = accountsRepository.save(account);

        var response = new AccountsCancelResponse();
        response.setInternalCode(account.getInternalCode());
        response.setMessage("Conta cancelada com sucesso");
        return response;
    }

}
