package kr.co.thecheck.domainh2.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.thecheck.domainh2.api.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
class AuthControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthService authService;

    @Test
    public void 인증코드발급_성공() throws Exception{
        // Given
        AuthService.RequestAuthCodeDto dto = new AuthService.RequestAuthCodeDto();
        dto.setUsername("test");
        dto.setPassword("1234");

        AuthService.AuthCode authCode = new AuthService.AuthCode();
        authCode.setAuthCode("12345");

        // When
        Mockito.when(authService.auth(dto)).thenReturn(authCode);

        // Then
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/oauth/authorize")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(mapper.writeValueAsString(dto))
                                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
    }

}