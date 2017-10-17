package com.apress.springrecipes.cloud.web;

import com.apress.springrecipes.cloud.Contact;
import com.apress.springrecipes.cloud.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactRepository contactRepository;


    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return "list";
    }


    @GetMapping("/new")
    public String newContact(Model model) {
        model.addAttribute(new Contact());
        return "contact";
    }

    @PostMapping("/new")
    public String newContact(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return "redirect:/contact";
    }

}
