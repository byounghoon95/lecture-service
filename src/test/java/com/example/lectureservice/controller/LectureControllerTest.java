package com.example.lectureservice.controller;

import com.example.lectureservice.controller.dto.LectureRequest;
import com.example.lectureservice.entity.LectureDetailEntity;
import com.example.lectureservice.entity.LectureEntity;
import com.example.lectureservice.service.LectureServiceImpl;
import com.example.lectureservice.service.domain.response.LectureApplyResponse;
import com.example.lectureservice.service.domain.response.LectureDetailsResponse;
import com.example.lectureservice.service.domain.response.LectureResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class LectureControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LectureServiceImpl lectureService;

    @DisplayName("특정 유저가 특강을 신청한다")
    @Test
    void apply() throws Exception {
        // given
        String lectureCode = "A00001";
        String title = "물리학";
        String name = "김재근";
        LocalDateTime date = LocalDateTime.of(2024, 6, 24, 13, 0,0);
        int maxParticipants = 30;
        int currParticipants = 5;

        String userId = "a1";

        LectureRequest request = LectureRequest.builder()
                .lectureCode(lectureCode)
                .userId(userId)
                .build();

        LectureDetailEntity detail = LectureDetailEntity.builder()
                .name(name)
                .maxParticipants(maxParticipants)
                .currParticipants(currParticipants)
                .date(date)
                .build();

        List<LectureDetailEntity> details = List.of(detail);

        LectureEntity entity = LectureEntity.builder()
                .lectureCode(lectureCode)
                .title(title)
                .details(details)
                .build();

        // when
        when(lectureService.applyToLecture(any())).thenReturn(LectureApplyResponse.of(entity));

        // then
        mockMvc.perform(post("/lectures/apply")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.date").value(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.currParticipants").value(currParticipants));
    }

    @DisplayName("유저의 특강 신청 여부를 조회한다")
    @Test
    void isApplied_return_true() throws Exception {
        // given
        String userId = "a1";
        String lectureCode = "A00001";
        String title = "물리학";

        LectureApplyResponse response = LectureApplyResponse.builder()
                .lectureCode(lectureCode)
                .title(title)
                .build();

        List<LectureApplyResponse> list = List.of(response, response);

        // when
        when(lectureService.isAppliedLecture(any())).thenReturn(list);

        // then
        mockMvc.perform(get("/lectures/application/{userId}", userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].lectureCode").value(lectureCode))
                .andExpect(jsonPath("$[0].title").value(title))
        ;
    }

    @DisplayName("신청 가능한 특강 목록을 조회한다")
    @Test
    void selectLectureList() throws Exception {
        // given
        String lectureCode1 = "A00001";
        String lectureCode2 = "A00002";
        String title1 = "물리학";
        String title2 = "화학";
        String name = "김재근";
        LocalDateTime date = LocalDateTime.of(2024, 6, 24, 13, 0,0);

        List<LectureResponse> responses = List.of(
                new LectureResponse(lectureCode1, title1, List.of(
                        new LectureDetailsResponse(name, date, 30, 20),
                        new LectureDetailsResponse(name, date, 30, 20))),
                new LectureResponse(lectureCode2, title2, List.of(new LectureDetailsResponse(name, date, 30, 10)))
        );
        // when
        when(lectureService.selectLectureList()).thenReturn(responses);

        // then
        mockMvc.perform(get("/lectures"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lectureCode").value("A00001"))
                .andExpect(jsonPath("$[0].title").value("물리학"))
                .andExpect(jsonPath("$[1].lectureCode").value("A00002"))
                .andExpect(jsonPath("$[1].title").value("화학"))
                .andExpect(jsonPath("$[0].details", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[1].details", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].details[0].currParticipants").value(20))
                .andExpect(jsonPath("$[1].details[0].currParticipants").value(10));

    }
}