package com.trelloclone.demo.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trelloclone.demo.models.Account;
import com.trelloclone.demo.models.Card;
import com.trelloclone.demo.models.Checklist;
import com.trelloclone.demo.models.Label;
import com.trelloclone.demo.repositories.AccountRepository;
import com.trelloclone.demo.repositories.CardRepository;
import com.trelloclone.demo.repositories.ChecklistRepository;
import com.trelloclone.demo.repositories.LabelRepository;

@RestController
@CrossOrigin
@RequestMapping("cards")
public class CardController {
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private LabelRepository labelRepository;
	@Autowired
	private ChecklistRepository checklistRepository;

	@GetMapping
	public List<Card> getAll(){
		return cardRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{id}")
	public Card getById(@PathVariable Long id){
		return cardRepository.getOne(id);
	}

	@PostMapping
	public Card create(@RequestBody final Card card){
		return cardRepository.saveAndFlush(card);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id){
		cardRepository.deleteById(id);
	}

	@RequestMapping( method = RequestMethod.PUT)
	public Card update(@RequestBody Card card){
		Card existingCard = cardRepository.getOne(card.getId());
	    BeanUtils.copyProperties(card, existingCard, "id","position","status");
	    return cardRepository.saveAndFlush(existingCard);
	}

	@PostMapping(value = "add-member")
	public Card addMember(@RequestBody Map<String, Object> payload) {
		Card card = cardRepository.getOne(Long.parseLong(payload.get("cardId").toString()));
	    Set<Account> members = card.getMembers();
	    if(members == null){
	    	members = new HashSet<>();
	    }
	    members.add(accountRepository.getOne(payload.get("accountUsername").toString()));
	    card.setMembers(members);
	    return cardRepository.saveAndFlush(card);
	}

	@PostMapping(value = "add-label")
	public Card addLabel(@RequestBody Map<String, Long> payload) {
		Card card = cardRepository.getOne(payload.get("cardId"));
	    Set<Label> labels = card.getLabels();
	    if(labels == null){
	    	labels = new HashSet<>();
	    }
	    labels.add(labelRepository.getOne(payload.get("labelId")));
	    card.setLabels(labels);
	    return cardRepository.saveAndFlush(card);
	}

	@PostMapping(value = "reorder-checklist")
	public Card addChecklist(@RequestBody Card payload){
		Card card = cardRepository.getOne(payload.getId());
	    List<Checklist> checklists = payload.getChecklists();
	    if(checklists == null){
	    	checklists = new ArrayList<>();
	    }
	    for(int i=0;i<checklists.size();i++){
	    	checklists.get(i).setPosition((short)i);
	    }
	    card.setChecklists(checklists);
	    return cardRepository.saveAndFlush(card);
	}

	@Transactional
	@DeleteMapping("{card_id}/checklist")
	public Long deleteChecklistByCardId(@PathVariable Long cardId){
		return checklistRepository.deleteByCardId(cardId);
	}
}
