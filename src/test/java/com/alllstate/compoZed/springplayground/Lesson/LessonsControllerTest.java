package com.alllstate.compoZed.springplayground.Lesson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by localadmin on 7/18/17.
 */
@WebMvcTest(LessonsController.class)
@RunWith(SpringRunner.class)
public class LessonsControllerTest {


    @Autowired
    public  MockMvc mockMvc;

    @MockBean
    public LessonRepository repository;

    @Test
    public void createDelegateToRepository() throws Exception{

        when(repository.save(any(LessonModel.class))).then(returnsFirstArg());
        final MockHttpServletRequestBuilder post = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Mock another one\"}");
        final ResultActions resultActions = mockMvc.perform(post);
                resultActions.andExpect(status().isOk())
                        .andExpect(jsonPath("$.title", is("Mock another one")));

                verify(repository).save(any(LessonModel.class));

    }


    //test if return a list of lessonModel
    @Test
    public void getDelegateToRepository() throws Exception{

        when(this.repository.save(any(LessonModel.class))).then(returnsFirstArg());
        final MockHttpServletRequestBuilder update = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON).content("{\"title\": \"Mock another one\"}");
        final ResultActions res = mockMvc.perform(update)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is("Mock another one")));
        verify(repository).save(any(LessonModel.class));
    }

}