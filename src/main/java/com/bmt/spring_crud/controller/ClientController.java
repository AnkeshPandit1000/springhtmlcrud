package com.bmt.spring_crud.controller;

import com.bmt.spring_crud.dto.ClientDto;
import com.bmt.spring_crud.entity.Clients;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.bmt.spring_crud.repository.ClientRepository;

import javax.script.Bindings;
import java.util.Date;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    public ClientRepository clientrepo;

    @GetMapping({"", "/"})
    public String getClients(Model model) {
        var clients = clientrepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("clients", clients);

        return "clients/showtable";
    }

    @GetMapping("/create")
    public String createClient(Model model) {
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);

        return "clients/create";
    }

    @PostMapping("/create")
    public String createClient(@Valid @ModelAttribute ClientDto clientDto, BindingResult result) {
        // For Showing Error
        if(clientrepo.findByEmail(clientDto.getEmail()) != null) {
            result.addError(
                    new FieldError("clienDto", "email", clientDto.getEmail(),
                            false, null, null, "Email is already Exists")
            );
        }

        if(result.hasErrors()) {
            return "clients/create";
        }

        Clients client = new Clients();
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setStatus(clientDto.getStatus());
        client.setCreatedAt(new Date());

        clientrepo.save(client);

        return "redirect:/clients";
    }

    @GetMapping("/edit")
    public String editClient(Model model, @RequestParam int id) {
        Clients client = clientrepo.findById(id).orElse(null);

        if(client == null) {
            return "redirect:/clients";
        }

        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setFirstname(client.getFirstname());
        clientDto.setLastname(client.getLastname());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setAddress(client.getAddress());
        clientDto.setStatus(client.getStatus());
        clientDto.setCreatedAt(client.getCreatedAt());

        model.addAttribute("client", client);
        model.addAttribute("clientDto", clientDto);

        return "clients/edit";
    }

    @PostMapping("/edit")
    public String editClient(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ClientDto clientDto,
            BindingResult result) {

        Clients client = clientrepo.findById(id).orElse(null);

        if(client == null) {
            return "redirect:/clients";
        }

        model.addAttribute("client", client);

        if(result.hasErrors()) {
            return "redirect:/edit";
        }

        //Edit the Client
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setStatus(clientDto.getStatus());

        try {
            clientrepo.save(client);
        }
        catch (Exception e) {
            result.addError(
                    new FieldError("clientDto", "email", clientDto.getEmail(),
                            false, null, null, "This Email is already Exists")
            );

            return "redirect:/edit";
        }

        return "redirect:/clients";
    }

    @GetMapping("/delete")
    public String deleteClient(@RequestParam int id) {

        Clients client = clientrepo.findById(id).orElse(null);

        if(client != null) {
            clientrepo.delete(client);
        }

        return "redirect:/clients";
    }
}
