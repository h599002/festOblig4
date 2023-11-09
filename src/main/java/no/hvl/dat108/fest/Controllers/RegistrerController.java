package no.hvl.dat108.fest.Controllers;


import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat108.fest.Service.UsersService;
import no.hvl.dat108.fest.Util.InputValidator;
import no.hvl.dat108.fest.Util.LoginUtil;
import no.hvl.dat108.fest.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegistrerController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/registrer")
    public String getRegistreringside()     {

        return "registreringsView.html";
    }
    @PostMapping("/registrer")
    public String sjekkRegistrering(
            @RequestParam String fornavn,
            @RequestParam String etternavn,
            @RequestParam String mobil,
            @RequestParam String passord,
            @RequestParam String passordRepetert,
            @RequestParam String kjonn,
            Model model,
            RedirectAttributes ra,
            HttpServletRequest request) {


        StringBuilder tilbakemeld = new StringBuilder();
        boolean harFeil = false;


        // Sjekk navn
        if(!InputValidator.erGyldigNavn(fornavn) || !InputValidator.erGyldigNavn(etternavn)){
            tilbakemeld.append("Ugyldig navn. ");
            harFeil = true;
        }

        // Sjekk mobilnummer
        if(!InputValidator.erGyldigMobil(mobil)){
            tilbakemeld.append("Ugyldig mobil. ");
            harFeil = true;
        }

        // Sjekk passord
        if(!(passordRepetert.equals(passord) && InputValidator.erGyldigPassord(passord))){
            tilbakemeld.append("Ugyldig passord. ");
            harFeil = true;
        }

        // Sjekk at kjønn er med. Kanskje ikke viktig å sjekke noe annet enn at det ikke er tomt
        if(!InputValidator.erGyldigKjonn(kjonn)){
            tilbakemeld.append("Ugyldig kjonn. ");
            harFeil = true;
        }

        if(!harFeil){
            UsersModel deltager = usersService.registererUser(fornavn, etternavn, mobil, passord, kjonn);
            if(deltager != null){
                LoginUtil.loggInnBruker(request, deltager);
                ra.addFlashAttribute("navn", fornavn + " " + etternavn);
                ra.addFlashAttribute("mobil", mobil);
                ra.addFlashAttribute("kjonn", kjonn);

                request.getSession().setAttribute("mobil",mobil);

                return "redirect:/paameldt";
            }
            tilbakemeld.append("Nummer er allerede registrert. Registrering var ikke vellykket.");
            ra.addFlashAttribute("feilMelding", tilbakemeld.toString());
            return "redirect:/registrer";
        } else {
            tilbakemeld.append("Registrering var ikke vellykket.");
            ra.addFlashAttribute("feilMelding", tilbakemeld.toString());
            return "redirect:/registrer";
        }

    }
}
