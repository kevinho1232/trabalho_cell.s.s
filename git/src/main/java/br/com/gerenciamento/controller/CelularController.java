package br.com.gerenciamento.controller;

import br.com.gerenciamento.repository.CelularRepository; 
import br.com.gerenciamento.enums.Marca;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.model.Celular;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CelularController { 

    @Autowired
    private CelularRepository celularRepository;  

   @GetMapping("/inserirCelular")
public ModelAndView insertCelular(Celular celular) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("Celular/formCelular");

    // Criando um novo celular para preencher o formulário
    modelAndView.addObject("celular", new Celular());

    // Passando a lista de marcas (valores da enum Marca)
    modelAndView.addObject("marcas", Marca.values());
    
    // Passando a lista de status (valores da enum Status)
    modelAndView.addObject("status", Status.values()); 

    return modelAndView;
}



    @PostMapping("InsertCelular")
    public ModelAndView inserirCelular(@Valid Celular celular, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("Celular/formCelular");
            modelAndView.addObject("celular");
        } else {
            modelAndView.setViewName("redirect:/celulares-adicionados"); 
            celularRepository.save(celular);
        }
        return modelAndView;
    }

    @GetMapping("celulares-adicionados")
    public ModelAndView listagemCelulares() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Celular/listCelulares");  
        modelAndView.addObject("celularesList", celularRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Celular/editar");  
        Celular celular = celularRepository.getById(id);
        modelAndView.addObject("celular", celular);
        return modelAndView;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Celular celular) {
        ModelAndView modelAndView = new ModelAndView();
        celularRepository.save(celular);
        modelAndView.setViewName("redirect:/celulares-adicionados"); 
        return modelAndView;
    }

    @GetMapping("/remover/{id}")
    public String removerCelular(@PathVariable("id") Long id) {
        celularRepository.deleteById(id);
        return "redirect:/celulares-adicionados"; 
    }

    @GetMapping("/filtro-Celulares")
    public ModelAndView filtroCelulares() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Celular/filtroCelulares");
        return modelAndView;
    }
    

    @GetMapping("/celulares-ativos")
    public ModelAndView listaCelularesAtivos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Celular/celulares-ativos");
        modelAndView.addObject("celularesAtivos", celularRepository.findByStatusAtivo());
        return modelAndView;
    }
    


    @GetMapping("/celulares-inativos")
    public ModelAndView listaCelularesInativos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Celular/celulares-inativos");  
        modelAndView.addObject("celularesInativos", celularRepository.findByStatusInativo());
        return modelAndView;
    }

    @PostMapping("/pesquisar-celular")
    public ModelAndView pesquisarCelular(@RequestParam(required = false) String modelo) {
        ModelAndView modelAndView = new ModelAndView();
        List<Celular> listaCelulares;
        if (modelo == null || modelo.trim().isEmpty()) {
            listaCelulares = celularRepository.findAll();
        } else {
            listaCelulares = celularRepository.findByModeloContainingIgnoreCase(modelo);
        }
        modelAndView.addObject("ListaDeCelulares", listaCelulares);
        modelAndView.setViewName("Celular/pesquisa-resultado");  
        return modelAndView;
    }
}
