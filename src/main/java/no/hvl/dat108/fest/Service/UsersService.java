package no.hvl.dat108.fest.Service;



import no.hvl.dat108.fest.model.UsersModel;
import no.hvl.dat108.fest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    // Lage en static instans av passordservice for å kunne statisk kalle på metodene
    private static final PassordService passordService = new PassordService();

    public UsersService(UsersRepository usersRepository)        {
        this.usersRepository = usersRepository;
    }


    public UsersModel registererUser(String fornavn, String etternavn,
                                     String mobil, String passord, String kjonn) {
        if (usersRepository.findByMobil(mobil).isPresent()) {
            return null;
        }else{
            UsersModel usersModel = new UsersModel();
            usersModel.setFornavn(fornavn);
            usersModel.setEtternavn(etternavn);
            usersModel.setMobil(mobil);
            usersModel.setKjonn(kjonn);
            String salt = passordService.genererTilfeldigSalt();
            String hash = passordService.hashMedSalt(passord,salt);
            usersModel.setSalt(salt);
            usersModel.setHash(hash);

            return usersRepository.save(usersModel);


        }
    }

    public UsersModel authenticate (String mobil)       {
        return usersRepository.findByMobil(mobil).orElse(null);

    }
    public List<UsersModel> findAllDeltager(){
        return usersRepository.findAll();
    }
    public java.util.Optional<UsersModel> findByMobile(String mobil) {return usersRepository.findByMobil(mobil);}

}
