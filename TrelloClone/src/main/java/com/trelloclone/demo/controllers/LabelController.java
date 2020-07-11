package com.trelloclone.demo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trelloclone.demo.models.Label;
import com.trelloclone.demo.repositories.LabelRepository;

@RestController
@CrossOrigin
@RequestMapping("labels")
public class LabelController {
	@Autowired
    LabelRepository labelRepository;

    @GetMapping
    public List<Label> getAll() {
        return labelRepository.findAll();
    }

    @GetMapping("{id}")
    public Label getById(@PathVariable Long id) {
        return labelRepository.getOne(id);
    }

    @PostMapping
    public Label create(@RequestBody Label label){
        return labelRepository.saveAndFlush(label);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Label update(@RequestBody Label label){
        Label old=labelRepository.getOne(label.getId());
        BeanUtils.copyProperties(label, old, "id");
        return labelRepository.saveAndFlush(old);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        labelRepository.deleteById(id);
    }
}
