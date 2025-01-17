package com.cards.service;

import com.cards.model.Accounts;
import com.cards.repository.AccountsRepository;
import com.cards.request.AccountsCancelRequest;
import com.cards.request.AccountsCreateRequest;
import com.cards.util.AccountsStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountsServiceTest {

    @Mock
    private AccountsRepository accountsRepository;

    @InjectMocks
    private AccountsService accountService;

    private AccountsCreateRequest createRequest;

    private AccountsCancelRequest cancelRequest;

    private Accounts account;


    @BeforeEach
    void setUp() {
        createRequest = new AccountsCreateRequest();
        createRequest.setName("Luciano Lopes");
        createRequest.setCpf("12345678900");
        createRequest.setCellphone("11987654321");
        createRequest.setEmail("teste@email.com");

        cancelRequest = new AccountsCancelRequest();
        cancelRequest.setInternalCode(UUID.randomUUID().toString());

        account = new Accounts();
        account.setName("Luciano Lopes");
        account.setCpf("12345678900");
        account.setCellphone("11987654321");
        account.setEmail("teste@email.com");
        account.setInternalCode(UUID.randomUUID().toString());
        account.setStatus(AccountsStatus.ACTIVE.getValue());
    }

    @Test
    @DisplayName("Verificando se CPF já existe")
    void testCpfExists() {
        when(accountsRepository.existsByCpf(createRequest.getCpf())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accountService.create(createRequest);
        });

        assertEquals("CPF já cadastrado", exception.getMessage());
    }

    @Test
    @DisplayName("Criando conta")
    void testCreate() {
        when(accountsRepository.existsByCpf(createRequest.getCpf())).thenReturn(false);
        when(accountsRepository.existsByCellphone(createRequest.getCellphone())).thenReturn(false);
        when(accountsRepository.existsByEmail(createRequest.getEmail())).thenReturn(false);
        when(accountsRepository.save(any(Accounts.class))).thenReturn(account);

        var response = accountService.create(createRequest);

        assertNotNull(response);
        assertEquals(account.getInternalCode(), response.getInternalCode());
    }

}
