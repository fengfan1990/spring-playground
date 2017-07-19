package com.alllstate.compoZed.springplayground.Lesson;

/**
 * Created by localadmin on 7/19/17.
 */

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Map;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerRealDatabaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LessonRepository repository;

    @Transactional
    @Rollback
    @Test
    public void readReturnExistingLessons() throws Exception{
        LessonModel lessonModel1 = new LessonModel();
        LessonModel lessonModel2 = new LessonModel();
        lessonModel1.setTitle("lesson 1");
        lessonModel2.setTitle("lesson 2");

        repository.save(Arrays.asList(lessonModel1,lessonModel2));

        mockMvc.perform(get("/lessons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("lesson 1")));
    }


    @Test
    public void updateReturnExistingLessons() throws Exception{
        LessonModel lessonModel1 = new LessonModel();
        LessonModel lessonModel2 = new LessonModel();
        lessonModel1.setTitle("lesson 1");
        lessonModel2.setTitle("lesson 2");
        lessonModel1.setId(1L);
        lessonModel2.setId(2L);

        repository.save(Arrays.asList(lessonModel1,lessonModel2));

        mockMvc.perform(put("/lessons/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Lessons 2\", \"id\":\"2\"}"))
                .andExpect(jsonPath("$.id", is(lessonModel1.getId())))
                .andExpect(jsonPath("$.title", is("Lessons 2")));
    }

}
