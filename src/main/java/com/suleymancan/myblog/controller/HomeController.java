package com.suleymancan.myblog.controller;

import com.suleymancan.myblog.model.Entry;
import com.suleymancan.myblog.repository.EntryRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

//logger basmak için
@Slf4j
@Controller
@RequestMapping("/blog")
public class HomeController {

    //tüm bean'ler, componentler  apllicationContext'in icindeki pool'de tutulur.
    @Autowired
    ApplicationContext applicationContext;

    //tüm bean'ler
    @RequestMapping("/beans")
    public String allBean(Model model){
        String [] allBeans=applicationContext.getBeanDefinitionNames();
        int beansCount=applicationContext.getBeanDefinitionCount();
        model.addAttribute("beans",allBeans);
        model.addAttribute("beansCount",beansCount);

        return"allBeans/beans";
    }



    @Autowired
    private EntryRepository entryRepoitory;
    //blog ana sayfa
    @RequestMapping(value="",method = RequestMethod.GET)
    public String getIndex(Model model){
        Iterable<Entry>entries=entryRepoitory.findAll();
        model.addAttribute("entries",entries);

        return "entries/listEntries";
    }

    @RequestMapping(value="/new",method= RequestMethod.GET)
    public String getEntryForm(Model model){
    model.addAttribute("entry",new Entry());


    return "entries/newEntry";
}
    //formdan datayı karsılama.
    //binding olayı server side'da validasyon.
    @RequestMapping(value="/new",method = RequestMethod.POST)
    public String postEtryForm(@Valid @ModelAttribute Entry entry, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "entries/newEntry";
        }
        else {
            entryRepoitory.save(entry);
            return "redirect:/blog";
        }
        }

        //spEL
        //pathVariable ile Integer id mapledik.
        @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String showEntry(@PathVariable("id") Integer id, Model model){
        //entry bos mu vs.
        Optional<Entry> entryOptional=entryRepoitory.findById(id);
        if(!entryOptional.isPresent()){
            log.warn("Entry with{} id is not present",id);
            return  "index";
        }
        else{
            model.addAttribute("entry",entryOptional.get());
            return "entries/showEntry";
        }


        }

        @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String getUpdateEntry(@PathVariable("id") Integer id, Model model){
            Optional<Entry> entryOptional=entryRepoitory.findById(id);
            if(!entryOptional.isPresent()){
                log.warn("Entry with{} id is not present",id);
                return  "index";
            }
            else{
                model.addAttribute("entry",entryOptional.get());
                return "entries/updateEntry";
            }

        }

        @RequestMapping(value="/{id}/update",method = RequestMethod.POST)
        public String postUpdateEntry(@Valid @ModelAttribute Entry entry, BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                return "entries/updateEntry";
            }
            else {
                entryRepoitory.save(entry);
                return "redirect:/blog";
            }        }


            @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
            public String deleteEntry(@PathVariable("id") Integer id){
                Optional<Entry> entryOptional=entryRepoitory.findById(id);
                if(!entryOptional.isPresent()){
                    log.warn("Entry with{} id is not present",id);
                    return  "redirect:/blog";
                }
                else{
                    entryRepoitory.delete(entryOptional.get());
                    return "redirect:/blog";
                }
            }



}
