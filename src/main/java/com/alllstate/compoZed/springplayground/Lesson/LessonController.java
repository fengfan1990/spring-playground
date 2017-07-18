package com.alllstate.compoZed.springplayground.Lesson;

import org.springframework.web.bind.annotation.*;


/**
 * Created by localadmin on 7/18/17.
 */
@RestController
@RequestMapping("/lessons")
    final class LessonsController {

        private final LessonRepository repository;

        public LessonsController(LessonRepository repository) {
            this.repository = repository;
        }

        @GetMapping("")
        public Iterable<LessonModel> all() {
            return this.repository.findAll();
        }

        @PostMapping("")
        public LessonModel create(@RequestBody LessonModel lesson) {
            return this.repository.save(lesson);
        }

    }

