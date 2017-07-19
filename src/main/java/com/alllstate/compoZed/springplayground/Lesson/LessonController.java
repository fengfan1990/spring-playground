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

    @GetMapping("/{id}")
    public LessonModel read(@PathVariable long id) {
        return repository.findOne(id);
    }


    @PutMapping("/update/{id}")
    public LessonModel update(@RequestBody LessonModel lessonModel, @PathVariable long id) {
        if(repository.findOne(id) != null) {
            LessonModel findLesson = repository.findOne(id);
            findLesson.setTitle(lessonModel.getTitle());
            return repository.save(findLesson);
        }
        return lessonModel;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        if(repository.findOne(id) != null){
            repository.delete(id);
        }

    }


}

