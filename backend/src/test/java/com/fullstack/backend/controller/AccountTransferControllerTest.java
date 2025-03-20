package com.fullstack.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.backend.model.AccountTransfer;
import com.fullstack.backend.service.AccountTransferService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountTransferController.class)
public class AccountTransferControllerTest {

    private final String BASE_URL = "/api/accountTransfer";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountTransferService service;

    private AccountTransfer createAccountTransfer() {
        return new AccountTransfer(null, null, null, null, null, null, null);
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createAccountTransfer_HttpStatusShouldOk() throws Exception {
    	AccountTransfer accountTransfer = createAccountTransfer();
    	accountTransfer.setId(1l);

        mockMvc.perform(post(BASE_URL + "/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(accountTransfer)))

                .andExpect(status().isOk());
    }

    @Test
    public void updateAccountTransfer_HttpStatusShouldOk() throws Exception {
    	AccountTransfer accountTransfer = createAccountTransfer();
    	accountTransfer.setId(1l);

        mockMvc.perform(put(BASE_URL + "/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(accountTransfer)))

                .andExpect(status().isOk());
    }

    @Test
    public void deleteAccountTransfer_HttpStatusShouldOk() throws Exception {
    	AccountTransfer accountTransfer = createAccountTransfer();
    	accountTransfer.setId(1l);

        mockMvc.perform(delete(BASE_URL + "/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", accountTransfer.getId().toString()))

                .andExpect(status().isOk());
    }

    @Test
    public void getAccountTransferById_HttpStatusShouldOk() throws Exception {
    	AccountTransfer accountTransfer = createAccountTransfer();
    	accountTransfer.setId(1l);

        mockMvc.perform(get(BASE_URL + "/get")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", accountTransfer.getId().toString()))

                .andExpect(status().isOk());
    }

    @Test
    public void listAccountTransfers_HttpStatusShouldOk() throws Exception {

        mockMvc.perform(get(BASE_URL + "/list")
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }


}
