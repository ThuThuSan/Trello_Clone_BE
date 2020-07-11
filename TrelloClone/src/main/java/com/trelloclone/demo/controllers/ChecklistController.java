package com.trelloclone.demo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trelloclone.demo.models.Checklist;
import com.trelloclone.demo.repositories.ChecklistRepository;

@RestController
@CrossOrigin
@RequestMapping("checklists")
public class ChecklistController {
	@Autowired
    ChecklistRepository checklistRepository;

    @PostMapping
    public Checklist create(@RequestBody Checklist checklist){
        return checklistRepository.saveAndFlush(checklist);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        checklistRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Checklist update(@RequestBody Checklist checklist){
        Checklist old = checklistRepository.getOne(checklist.getId());
        BeanUtils.copyProperties(checklist, old, "id", "cardId", "position", "title");
        return checklistRepository.saveAndFlush(old);
    }

    @GetMapping("{card_id}")
    public List<Checklist> getByCardId(@PathVariable Long card_id){
        return checklistRepository.findByCardId(card_id);
    }

    @PostMapping("{card_id}")
    public List<Checklist> createAll(@RequestBody List<Checklist> checklists){
        return checklistRepository.saveAll(checklists);
    }
}
