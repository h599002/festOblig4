package no.hvl.dat108.fest.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.fest.Service.PassordService;
import no.hvl.dat108.fest.Service.UsersService;
import no.hvl.dat108.fest.Util.InputValidator;
import no.hvl.dat108.fest.Util.LoginUtil;
import no.hvl.dat108.fest.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsersService usersService;

    private final PassordService passordService = new PassordService();

    @GetMapping("/login")
    public String getLoginSide(Model model)     {
        return "loginView";
    }
    @GetMapping("/paameldt")
    public String getPaameldtSide(Model model){
        return "paameldtView";
    }
    @GetMapping("/loggUt")
    public String loggUt(HttpSession session, RedirectAttributes ra){
        LoginUtil.loggUtBruker(session);
        ra.addFlashAttribute("loggUtMelding", "Du er logget ut!");
        return "redirect:login";
    }
    @GetMapping("/deltagerliste")
    public String deltagerListe(Model model, HttpServletRequest request){
        String mobil = (String) request.getSession().getAttribute("mobil");
        model.addAttribute("mobil",mobil);

        List<UsersModel> liste = usersService.findAllDeltager();
        model.addAttribute("deltagere",liste);

        return "deltagerListeView";
    }


    @PostMapping("/login")
    public String loggInnForsok(
            @RequestParam String mobil,
            @RequestParam String passord,
            HttpServletRequest request,
            RedirectAttributes ra
    ){
        StringBuilder stringBuilder;
        boolean harFeil = false;

        if(!InputValidator.erGyldigMobil(mobil) || !InputValidator.erGyldigPassord(passord)) {
            ra.addFlashAttribute("feilMelding", "Ugyldig mobilnummer/passord");
            return "redirect:login";
        }

        Optional<UsersModel> optional = usersService.findByMobile(mobil);
        if(optional.isEmpty()){
            ra.addFlashAttribute("feilMelding", "Bruker eksisterer ikke");
            return "redirect:login";
        }
        UsersModel deltager = optional.get();
        String hash = deltager.getHash();
        String salt = deltager.getSalt();
        
        if(passordService.erKorrektPassord(passord,salt,hash)){
            LoginUtil.loggInnBruker(request, deltager);
            request.getSession().setAttribute("mobil", mobil);
            return "redirect:deltagerliste";
        }
        ra.addFlashAttribute("feilMelding", "Passord stemmer ikke");
        return "redirect:login";
    }



}